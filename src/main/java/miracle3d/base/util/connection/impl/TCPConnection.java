package miracle3d.base.util.connection.impl;


import miracle3d.base.util.connection.ConnectionException;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPConnection extends SocketConnection {
    private Socket socket;
    protected OutputStream out;
    protected InputStream in;

    public TCPConnection(String host, int port) {
        super(host, port);
    }

    public void establishConnection() throws ConnectionException {
        try {
            this.socket = new Socket(this.host, this.port);
            this.socket.setTcpNoDelay(true);
            this.in = new BufferedInputStream(this.socket.getInputStream());
            this.out = new BufferedOutputStream(new DataOutputStream(this.socket.getOutputStream()));
            this.connected = true;
        } catch (UnknownHostException var3) {
            throw new ConnectionException(var3.getMessage(), var3);
        } catch (ConnectException var4) {
            String message = this.host + ":" + this.port + " refused the connection. Is the server running?";
            throw new ConnectionException(message, var4);
        } catch (IOException var5) {
            var5.printStackTrace();
            throw new ConnectionException("IO problems", var5);
        }
    }

    public void sendMessage(byte[] msg) throws ConnectionException {
        byte[] body = msg;

        try {
            this.out.write(body);
            this.out.flush();
        } catch (IOException var4) {
            this.shutDown = true;
            throw new ConnectionException("Error writing to socket, shutting down...", var4);
        }
    }

    public void startReceiveLoop() throws ConnectionException {
        try {
            while(!this.shutDown) {
                byte[] currentMessage = this.receiveMessage();
                this.observer.onStateChange(currentMessage);
            }
        } finally {
            this.closeConnection();
            this.shutDown = false;
        }

    }

    protected byte[] receiveMessage() throws ConnectionException {
        try {
            int length = 16384;
            byte[] buffer = new byte[length];
            int total = this.in.read(buffer, 0, length);
            if (total < 0) {
                this.shutDown = true;
                throw new ConnectionException("Server shut down");
            } else {
                byte[] result = new byte[total];
                System.arraycopy(buffer, 0, result, 0, total);
                return result;
            }
        } catch (IOException var5) {
            this.shutDown = true;
            throw new ConnectionException("Error when reading from socket, closing down...", var5);
        }
    }

    public void closeConnection() {
        try {
            this.connected = false;
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}