import javax.swing.*;
import java.awt.*;

/**
 * Classe gérant l'interface graphique du jeu Tic Tac Toe.
 * Elle permet d'afficher le menu de sélection du rôle du joueur,
 * d'afficher la grille de jeu et de mettre à jour l'affichage en fonction des actions des joueurs.
 */
public class TicTacToeGUI {
    
    private TicTacToeGame game; //Gère la logique du jeu.
    private JFrame window; //Fenêtre principale.
    private JButton[][] buttons; //Tableau des boutons de la grille.
    private int gameSize;

    /**
     * Constructeur de la classe TicTacToeGUI.
     * 
     * @param size Taille de la grille de jeu.
     * @param game Référence à classe TicTacToeGame.
     */
    public TicTacToeGUI(int size, TicTacToeGame game)
    {
        this.gameSize = size;
        this.game = game;
        initializeWindow();
    }

    /**
     * Initialise la fenêtre principale du jeu ainsi que la grille.
     */
    private void initializeWindow()
    {
        window = new JFrame("Tic Tac Toe");
        buttons = new JButton[gameSize][gameSize];
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 150);
        window.setLocationRelativeTo(null);

        startMenu();
        window.setVisible(true);
    }

    /**
     * Menu de démarrage qui affiche le menu de sélection du rôle du joueur (X ou O).
     * Contient un panneau pour la sélection du rôle, et un pour le boutton de confirmation.
     */
    private void startMenu()
    {
        //Initialisation des panneaux
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1)); //Séparer le menu en 2 lignes et 1 colonne.
        JPanel rolePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        //Gestion du panneau de sélection du rôle.
        JLabel selectRole = new JLabel("Select your role");
        selectRole.setFont(new Font("Tahoma", Font.BOLD, 20));
        
        ButtonGroup roleSelection = new ButtonGroup();
        JRadioButton roleX = new JRadioButton("X", true);
        JRadioButton roleO = new JRadioButton("O");
        roleX.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        roleO.setFont(new Font("Tahoma", Font.BOLD, 16));

        roleSelection.add(roleX);
        roleSelection.add(roleO);
       
        rolePanel.add(selectRole);
        rolePanel.add(roleX);
        rolePanel.add(roleO);
        
        //Gestion du panneau pour le boutton de confirmation.
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        confirmButton.addActionListener(e ->
        {
            //Quand appui sur le bouton, lancer la partie.
            char playerRole = roleX.isSelected() ? 'X' : 'O';
            window.remove(menuPanel);
            gameBoard(playerRole);
        });

        //Ajout des composants dans la fenêtre.
        buttonPanel.add(confirmButton);
        menuPanel.add(rolePanel);
        menuPanel.add(buttonPanel);
        window.add(menuPanel, BorderLayout.CENTER);
    }

    /**
     * Initialise et affiche la grille de jeu après la sélection du rôle.
     * 
     * @param playerRole Rôle choisi pour le joueur sélectionné.
     */
    private void gameBoard(char playerRole)
    {
        //Initialisation du panneau de jeu.
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(gameSize, gameSize));

        int fontSize = Math.max(window.getHeight() / gameSize, 20);

        //Initialisation de chaque boutton de la grille.
        for (int i = 0; i < gameSize; i++)
        {
            for (int j = 0; j < gameSize; j++)
            {
                buttons[i][j] = createButton(i, j, fontSize);
                gamePanel.add(buttons[i][j]);
            }
        }
    
        //Ajout des composants dans la fenêtre.
        window.add(gamePanel, BorderLayout.CENTER);
        window.setSize(700, 700);
        window.setLocationRelativeTo(null);
        reloadWindow();

        game.startGame(playerRole);
    }

    /**
     * Crée un bouton pour la grille de jeu, changer son font et ajouter un listener.
     * 
     * @param row Ligne du bouton.
     * @param col Colonne du bouton.
     * @param fontSize Taille de la police.
     * @return Un bouton initialisé.
     */
    private JButton createButton(int row, int col, int fontSize)
    {
        JButton button = new JButton("");
        button.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        button.addActionListener(e -> game.playerTurn(row, col));
        return button;
    }

    /**
     * Met à jour l'affichage de la grille après qu'un joueur ait joué.
     * 
     * @param row Ligne où placer le symbole.
     * @param col Colonne où placer le symbole.
     * @param symbol Symbole du joueur ('X' ou 'O').
     */
    public void updateBoard(int row, int col, char symbol)
    {
        buttons[row][col].setText(String.valueOf(symbol));
        
        //Couleur de fond différente selon le pion placé.
        if (symbol == 'X')
            buttons[row][col].setBackground(new Color(230, 230, 250));
        else if (symbol == 'O')
            buttons[row][col].setBackground(new Color(229, 245, 250));

        buttons[row][col].repaint();
    }

    /**
     * Menu qui affiche un message de fin de partie et propose de rejouer ou quitter.
     * 
     * @param message Message indiquant le résultat de la partie.
     */
    public void endMenu(String message)
    {
        //Spécifier que les bouttons Oui et Non sont en anglais et non français.
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");

        int replay = JOptionPane.showConfirmDialog(window, message + " Play again?", "Game Over", JOptionPane.YES_NO_OPTION);      

        if (replay == 0)
            restartGame();
        else
            System.exit(0);
    }

    /**
     * Redémarre une nouvelle partie en réinitialisant l'interface et le jeu.
     */
    private void restartGame()
    {
        game.restart();
        window.dispose();
        initializeWindow(); 
    }
  

    /**
     * Rafraîchit la fenêtre.
     */
    private void reloadWindow()
    {
        window.revalidate();
        window.repaint();
    }
}