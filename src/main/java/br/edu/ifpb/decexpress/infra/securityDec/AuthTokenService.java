package br.edu.ifpb.decexpress.infra.securityDec;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AuthTokenService {
    @Value("${url.auth}")
    private String urlAuth;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private Gson gson;

    private static final Logger logger = Logger.getLogger(AuthTokenService.class.getName());

    public boolean authToken(TokenDTO token) {

        try {
            URL url = new URL(urlAuth);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
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
            TokenResponse retorno = gson.fromJson(response.toString().trim(), TokenResponse.class);
            return retorno.isValid();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsuarioLogadoDTO obterInfosUserLogado() {
        String auth = httpServletRequest.getHeader("Authorization");
        UsuarioLogadoDTO retorno = null;
        Optional<String> authOptional = Optional.ofNullable(auth);
        if (authOptional.isPresent() && !authOptional.get().isEmpty()) {
            String token = authOptional.get().substring("Bearer ".length()).trim();
            token = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);
            retorno = gson.fromJson(token, UsuarioLogadoDTO.class);

        }
        return retorno;
    }
}
