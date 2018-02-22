/*Purpose is to open a python terminal */
public class openPython{
public static void main(final String[] args){
    executeCommand("python hello.py");
}

private static void executeCommand(final String command){
    final Process p;
    try{
    	//open a python terminal
        p = Runtime.getRuntime().exec("cmd /k start python -i ");
        p.waitFor();
        //bind stream to the process
        Thread bindIn = new Thread(() -> {
            try{
                int av;
                while(!Thread.interrupted())
                while((av = System.in.available()) > 0){
                    byte[] bytes = new byte[av];
                    System.in.read(bytes);
                    p.getOutputStream().write(bytes);
                    p.getOutputStream().flush();
                }
                System.out.println("bindIn ended");
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
        });
        //bind stream to the process
        Thread bindOut = new Thread(() -> {
            try{
                int av;
                while(!Thread.interrupted())
                while((av = p.getInputStream().available()) > 0){
                    byte[] bytes = new byte[av];
                    p.getInputStream().read(bytes);
                    System.out.write(bytes);
                }
                System.out.println("bindOut ended");
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
        });

        bindIn.start();
        bindOut.start();
        p.waitFor();
        bindIn.interrupt();
        bindOut.interrupt();
    }catch(Exception e){
        e.printStackTrace();
    }
}}