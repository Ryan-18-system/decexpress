package br.edu.ifpb.decexpress.infra.securityDec;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class AuthTokenService {
    @Value("${url.auth}")
    private String urlAuth;
    public boolean authToken(TokenDTO token){

        try {
            URL url = new URL(urlAuth);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            Gson gson = new Gson();
            token.setToken(token.getToken().replace("Bearer ", ""));
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(gson.toJson(token));
            outputStream.flush();
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            TokenResponse retorno = gson.fromJson(response.toString().trim(),TokenResponse.class);
            return retorno.isValid();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
