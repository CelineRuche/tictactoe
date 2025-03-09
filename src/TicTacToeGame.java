/**
 * Cette classe gère le déroulement du jeu. Elle interagit avec la classe TicTacToeGUI pour l'affichage,
 * ainsi qu'avec la classe BoardManager pour les actions et modifications du board.
 */

public class TicTacToeGame {

    private BoardManager boardManager; //gère la logique du plateau
    private TicTacToeGUI ui; //gère l'affichage
    private final int gameSize;

    /**
     * Constructeur de la classe TicTacToeGame.
     * 
     * @param size Taille de la grille de jeu.
     */
    public TicTacToeGame(int size)
    {
        this.gameSize = size;
        this.ui = new TicTacToeGUI(size, this);
    }

    /**
     * Démarre la partie en définissant le rôle du joueur (X ou O).
     * Si le joueur a le rôle 'O', l'ordinateur joue immédiatement.
     *
     * @param playerRole Rôle du joueur ('X' ou 'O').
     */
    public void startGame(char playerRole)
    {
        //Si première partie, instantier le BoardManager. Sinon, simplement modifier le rôle choisi.
        if (boardManager == null)
            this.boardManager = new BoardManager(gameSize, playerRole);
        else
            boardManager.setRole(playerRole);
        
        if (playerRole == 'O') computerTurn();
    }

    /**
     * Gère le tour du joueur en plaçant son pion sur la grille.
     * Vérifie ensuite l'état du jeu (victoire, égalité, défaite, ou en cours).
     *
     * @param row Ligne sélectionnée.
     * @param col Colonne sélectionnée.
     */
    public void playerTurn(int row, int col)
    {
        //Récupérer le role du joueur.
        char move = boardManager.getCurrentRole();

        //Si le mouvement est valide, mettre à jour l'affichage de la grille et vérifier l'état du jeu.
        if (boardManager.playerTurn(row, col))
        {
            ui.updateBoard(row, col, move);

            int state = checkState();
            
            //Si le jeu est toujours en cours, lancer le tour de l'ordinateur après un délai.
            if (state == 2)
            {
                //Nouveau thread pour lancer le délai avant le tour de l'ordinateur, puis lancer le tour.
                new Thread(() -> {
                    try
                    {
                        Thread.sleep(200);
                        computerTurn();
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            } 
        }
    }

    /**
     * Gère le tour de l'ordinateur qui place un pion sur la grille selon la logique définie dans boardManager.
     */
    public void computerTurn()
    {
        char move = boardManager.getCurrentRole();

        int[] computerMove = boardManager.computerTurn();

        ui.updateBoard(computerMove[0], computerMove[1], move);

        checkState();
    }

    /**
     * Vérifie l'état actuel du jeu.
     * 
     * @return L'état du jeu : 1 pour victoire du joueur, -1 pour victoire de l'ordinateur, 0 pour égalité, 2 pour jeu en cours.
     */
    private int checkState()
    {
        int state = boardManager.checkState();
        if (state != 2) {
            showResult(state);
        }
        return state;
    }

    /**
     * Affiche le résultat du jeu (victoire, défaite, égalité).
     *
     * @param state L'état du jeu à afficher (1, -1, 0).
     */
    private void showResult(int state)
    {
        String message = (state == 1) ? "You Win!" : (state == -1) ? "Computer Wins!" : "It's a Tie!";
        ui.endMenu(message); //Afficher le message via l'ui.
    }

    /**
     * Redémarre une nouvelle partie en réinitialisant la grille.
     */
    public void restart()
    {
        boardManager.resetGrid();
    }
}
