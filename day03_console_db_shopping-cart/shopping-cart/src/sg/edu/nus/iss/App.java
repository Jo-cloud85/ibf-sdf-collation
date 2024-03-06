package sg.edu.nus.iss;

// 1. javac -d bin src/sg/edu/nus/iss/* 
// 2. java -cp bin sg.edu.nus.iss.App cartdb where cartdb is the extra parameter required by the task

public class App {

    private static String defaultdb= "db";
    
    public static void main( String[] args ) {
        if(args.length>0){
            //System.out.println("arg db directory from arg");
            System.out.println("Welcome to your shopping cart!");
            App.defaultdb = args[0];
        }

        ShoppingCartDB db = new ShoppingCartDB(defaultdb);
        Session session = new Session(db);
        session.start();
    }
}
