import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        ConnectionPool connectionPool = new ConnectionPool();

        for (int i = 0; i < 130; i++) {
            final int finalI = i;
            new Thread(
                    () -> {
                        try {
                            Connection connection = connectionPool.getConnection(finalI);
                            connection.connect();
                            connectionPool.returnConnection(connection);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
    }
}

class ConnectionPool {
    private List<Connection> connections = createConnections();

    private List<Connection> createConnections() {
        List<Connection> conns = new ArrayList<Connection>(5);
        for (int i = 0; i < 5; i++) {
            conns.add(new Connection());
        }
        return conns;
    }

    public Connection getConnection(int i) throws InterruptedException, CloneNotSupportedException {
        //System.out.println("get for " + i);
        synchronized (connections) {
            while (connections.isEmpty()) {
                connections.wait();
                System.out.print('.');
            }
            Connection remove = connections.remove(0);
            remove.setClient(i);
            return remove;
        }
    }

    public void returnConnection(Connection conn) {

        synchronized (connections) {
            connections.add(conn);
            connections.notify();
        }
    }
}

class Connection {

    int client;

    public void setClient(int client) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("was " + this.client + " become " + client);
        this.client = client;
    }

    public void connect() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(client + " connected!");
    }
}
