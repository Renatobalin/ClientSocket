package br.com.renato.client;

import br.com.renato.mensagem.Mensagem;
import br.com.renato.utilitarios.io.Console;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApp {

    public static void main(String[] args) {
        try {
            OutputStream saida;

            Socket socket = new Socket("localhost", 1234);

            saida = socket.getOutputStream();
            PrintStream print = new PrintStream(saida);
            print.println("DATA_HORA");

            InputStream in = socket.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            String resp = read.readLine();
            Console.escreverln("Resp: " + resp);

            print.println("MSG");

            Mensagem m = new Mensagem();
            m.setData(new Date());
            m.setDestino("Juca");
            m.setMsg("Olá estou chegando...");

            Gson gson = new Gson();
            print.println(gson.toJson(m));


            print.close();
            saida.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
