/**
 * Classe Board qui représente une grille.
 * Elle gère la logique des cellules vides, des mouvements et de l'état de la grille.
 */

public class Board {

    private final char[][] grid; //Grille de jeu représentée par un tableau de caractères.
    private final int size;
    private int emptyCells; //Compteur des cellules vides.

    /**
     * Constructeur de la classe Board.
     *
     * @param size Taille de la grille de jeu.
     */
    public Board(int size)
    {
        this.size = size;
        this.emptyCells = size * size;
        this.grid = new char[size][size];
        initializeGrid();
    }

    /**
     * Initialiser les cases de la grille avec un espace vide (' ').
     */
    private void initializeGrid()
    {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j] = ' ';
    }

    /**
     * Vérifie si la cellule spécifiée est vide.
     *
     * @param row Ligne de la cellule.
     * @param col Colonne de la cellule.
     * @return true si la cellule est vide, false sinon.
     */
    public boolean isCellEmpty(int row, int col)
    {
        return grid[row][col] == ' ';
    }

    /**
     * Vérifie si la grille est pleine (nombre de cellules vides = 0).
     *
     * @return true si la grille est plein, false sinon.
     */
    public boolean isFull()
    {
        return emptyCells == 0;
    }

    /**
     * Place un mouvement du joueur dans la cellule spécifiée et met à jour les cellules vides.
     *
     * @param row Ligne où jouer.
     * @param col Colonne où jouer.
     * @param move Le symbole à placer sur la cellule.
     */
    public void placeMove(int row, int col, char move)
    {
        grid[row][col] = move;
        emptyCells--;
    }

    /**
     * Récupère le contenu de la cellule spécifiée.
     *
     * @param row Ligne de la cellule.
     * @param col Colonne de la cellule.
     * @return Le caractère stocké dans la cellule.
     */
    public char getCell(int row, int col)
    {
        return grid[row][col];
    }

    /**
     * Retourne la taille de la grille.
     *
     * @return La taille dde la grille.
     */
    public int getSize() {
        return size;
    }

    /**
     * Vérifie si les indices de la cellule sont hors limite.
     *
     * @param row Ligne à vérifier.
     * @param col Colonne à vérifier.
     * @return true si les indices sont hors de portée, false sinon.
     */
    public boolean outOfRange(int row, int col)
    {
        return (row < 0 || row >= size || col < 0 || col >= size);
    }

    /**
     * Réinitialise la grille le compteur de cellules vides.
     */
    public void reset()
    {
        initializeGrid();
        emptyCells = size * size;
    }
}
