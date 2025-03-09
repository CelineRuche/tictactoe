public class TicTacToe {

    //Méthode principale pour lancer l'application
    public static void main(String[] args)
    {
        //Le jeu peut être lancé soit sans argument, soit avec un argument qui spécifie la taille.
        if (args.length > 1 )
        {
            System.out.println("You cannot launch the game this way");
            return;
        }

        //Taille par défaut = 3
        int gameSize = 3;

        if (args.length == 1)
        {
            try
            {
                gameSize = Integer.parseInt(args[0]);
                //Taille minimale de 3
                if (gameSize < 3)
                {
                    System.out.println("Grid size must be at least 3");
                    return;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("You cannot launch the game this way");
                return;
            }
        }
        
        new TicTacToeGame(gameSize);
    }
}
