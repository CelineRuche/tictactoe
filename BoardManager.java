import java.util.Random;

/**
 * Classe BoardManager qui gère la logique du jeu Tic Tac Toe.
 * Elle gère les actions du joueur et de l'ordinateur et vérifie l'état de la partie
 */
public class BoardManager {

    private Board board; //Grille du jeu
    private TurnManager gameState; //Gère l'état du jeu et le tour du joueur.
    private Random rand; //Générateur de nombres aléatoires pour les mouvements de l'ordinateur.
    private int lastRow, lastCol; //Position du dernier movement joué.

    /**
     * Constructeur de BoardManager.
     *
     * @param size Taille de la grille de jeu.
     * @param playerRole Rôle du joueur ('X' ou 'O').
     */
    public BoardManager(int size, char playerRole)
    {
        this.board = new Board(size);
        this.gameState = new TurnManager(playerRole);
        this.rand = new Random();
    }

    /**
     * Gère le tour du joueur.
     *
     * @param row Ligne où le joueur veut jouer.
     * @param col Colonne où le joueur veut jouer.
     * @return true si le coup est valide et joué, false sinon.
     */
    public boolean playerTurn(int row, int col)
    {
        //Vérifier si la case est vide, si elle est dans la grille et si c'est bien au tour du joueur.
        if (!board.isCellEmpty(row, col) || board.outOfRange(row, col) || !gameState.isPlayerTurn())
            return false;

        //Jouer le coup et change de tour.
        nextMove(row, col);
        return true;
    }

    /**
     * Gère le tour de l'ordinateur qui joue un coup aléatoire sur une case libre.
     *
     * @return La ligne et colonne du coup joué par l'ordinateur, ou null si ce n'est pas son tour.
     */
    public int[] computerTurn()
    {
        if (gameState.isPlayerTurn())
            return null;

        //Générer aléatoirement une case vide pour jouer.
        int row, col;
        do
        {
            row = rand.nextInt(board.getSize());
            col = rand.nextInt(board.getSize());
        }
        while (!board.isCellEmpty(row, col));

        //Jouer le coup et change de tour.
        nextMove(row, col);
        return new int[]{row, col};
    }

    /**
     * Joue un coup et met à jour les coordonnées du dernier coup joué.
     *
     * @param row Ligne où jouer.
     * @param col Colonne où jouer.
     */
    private void nextMove(int row, int col)
    {
        board.placeMove(row, col, gameState.getCurrentRole());
        lastRow = row;
        lastCol = col;
        gameState.switchTurn(); //Passer au tour suivant
    }

    /**
     * Vérifie l'état actuel du jeu.
     * 
     * @return L'état du jeu : 1 pour victoire du joueur, -1 pour victoire de l'ordinateur, 0 pour égalité, 2 pour jeu en cours.
     */
    public int checkState()
    {
        char lastMove = board.getCell(lastRow, lastCol);

        if (isCompleteLine(0, 1, lastMove) || //Vérification horizontale
            isCompleteLine(1, 0, lastMove) || //Vérification verticale
            isCompleteLine(1, 1, lastMove) || //Vérification première diagonale
            isCompleteLine(1, -1, lastMove)) //Vérification seconde diagonale
        {
            return (gameState.roleIsPlayer(lastMove)) ? 1 : -1; //Retourne 1 si le joueur gagne, -1 si l'ordinateur gagne.
        }

        if (board.isFull())
            return 0; //Match nul.
  
        return 2; //La partie continue.
    }

    /**
     * Vérifie si un joueur a complété une ligne en fonction de la direction donnée.
     * Calcule le nombre d'éléments qui se suivent dans la direction donnée,
     * en parcourant dans le sens indiqué et dans le sens inverse, et en faisant la somme.
     * On regarde ainsi si le nombre d'éléments qui se suivent correspond à la taille
     * du tableau. Si oui, le jeu est terminé.
     *
     * @param rowDirection Direction verticale (1 pour bas, -1 pour haut, 0 pour aucune direction verticale).
     * @param colDirection Direction horizontale (1 pour droite, -1 pour gauche, 0 pour aucune direction horizontale).
     * @param symbol Symbole du joueur ('X' ou 'O').
     * @return true si une ligne complète a été trouvée, false sinon.
     */
    private boolean isCompleteLine(int rowDirection, int colDirection, char symbol)
    {
        //Compteur à 1 = le dernier coup placé.
        int count = 1;

        int size = board.getSize();

         //Premières coordonnées à vérifier dans la direction donnée.
        int r = lastRow + rowDirection;
        int c = lastCol + colDirection;
        
        //Première boucle : vérifie les cases dans la direction spécifiée (droite, bas, ou une des diagonales).
        while (r >= 0 && r < size && c >= 0 && c < size && board.getCell(r, c) == symbol)
        {
            count++; //Incrémente le compteur car on a trouvé une case avec le même symbole.
            r += rowDirection;
            c += colDirection;
        }

        r = lastRow - rowDirection;
        c = lastCol - colDirection;

        //Deuxième boucle : vérifie les cases dans la direction opposée à celle du coup initial.
        while (r >= 0 && r < size && c >= 0 && c < size && board.getCell(r, c) == symbol)
        {
            count++;
            r -= rowDirection;
            c -= colDirection;
        }

        //Si le nombre de cases consécutives est ségal à la taille de la grille, une ligne complète est formée.
        return count == size;
    }

    /**
     * Retourne le symbole du joueur qui doit jouer.
     *
     * @return 'X' ou 'O'.
     */
    public char getCurrentRole()
    {
        return gameState.getCurrentRole();
    }

    /**
     * Définit le rôle du joueur ('X' ou 'O').
     *
     * @param playerRole Le rôle choisi.
     */
    public void setRole(char playerRole)
    {
        gameState.setRole(playerRole);
    }

    /**
     * Réinitialise la grille.
     */
    public void resetGrid()
    {
        board.reset();
    }
}
