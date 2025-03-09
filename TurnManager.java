/**
 * Classe TurnManager qui gère les rôles des joueurs et le tour de jeu.
 * Elle détermine quel joueur doit jouer à chaque tour.
 */

public class TurnManager {

    private char playerRole;
    private char computerRole;
    private boolean playerTurn;

    /**
     * Constructeur de la classe TurnManager.
     *
     * @param playerRole Rôle du joueur.
     */
    public TurnManager(char playerRole)
    {
        this.playerRole = playerRole;
        this.computerRole = (playerRole == 'X') ? 'O' : 'X';
        this.playerTurn = (playerRole == 'X');
    }

    /**
     * Retourne le rôle du joueur qui doit jouer.
     *
     * @return Le rôle du joueur actuel.
     */
    public char getCurrentRole()
    {
        return playerTurn ? playerRole : computerRole;
    }

    /**
     * Change de tour, le joueur qui doit jouer le prochain coup est inversé.
     */
    public void switchTurn()
    {
        playerTurn = !playerTurn;
    }

    /**
     * Vérifie si c'est actuellement le tour du joueur.
     *
     * @return true si c'est le tour du joueur, false sinon.
     */
    public boolean isPlayerTurn()
    {
        return playerTurn;
    }

    /**
     * Définit le rôle du joueur et met à jour celui de l'ordinateur.
     *
     * @param playerRole Le rôle du joueur choisi.
     */
    public void setRole(char playerRole)
    {
        this.playerRole = playerRole;
        this.computerRole = (playerRole == 'X') ? 'O' : 'X';
        this.playerTurn = (playerRole == 'X');
    }

    /**
     * Vérifie si le rôle correspond à celui du joueur.
     *
     * @param role Le rôle à vérifier.
     * @return true si le rôle correspond à celui du joueur, false sinon.
     */
    public boolean roleIsPlayer(char role)
    {
        return role == playerRole;
    }
}