/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner.restapiacces;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import mazerunner.HTTP400StatusException;
import mazerunner.restapiresponse.APIResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author kismo
 */
public class RestRequestsImpl implements RestRequests {

    @Override
    public APIResponse initMaze() throws HTTP400StatusException {
        //Building the method
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpPost post = new HttpPost(BASE_URL + "/api/init");

            //Trying to execute the request and reading the content out of it
            HttpResponse response = client.execute(post);
            Scanner sc = new Scanner(response.getEntity().getContent());
            String json = sc.nextLine();

            return parseFromJson(json);
            //TO DO : Handle exceptions separately
        } catch (IOException | NullPointerException ex) {
            throw new HTTP400StatusException("Could not get the expected"
                    + "response from the REST API", ex);
        }
    }

    @Override
    public APIResponse moveTo(String mazeGuid, String direction) throws HTTP400StatusException {
        //Building the method, and the URI
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            URIBuilder uri = new URIBuilder(BASE_URL + "/api/move");
            uri.setParameter("mazeGuid", mazeGuid).setParameter("direction", direction);
            HttpPost post = new HttpPost(uri.build());

            //Trying to execute the request and reading the content out of it
            HttpResponse response = client.execute(post);
            Scanner sc = new Scanner(response.getEntity().getContent());
            String json = sc.nextLine();

            return parseFromJson(json);

        } catch (IOException | NullPointerException | URISyntaxException ex) {
            throw new HTTP400StatusException("Could not get the expected"
                    + "response from the REST API", ex);
        }
    }

    @Override
    public APIResponse jumpTo(String mazeGuid, int x, int y) throws HTTP400StatusException {
        //Building the method, and the URI
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            URIBuilder uri = new URIBuilder(BASE_URL + "/api/jump");
            uri.setParameter("mazeGuid", mazeGuid).
                    setParameter("x", String.valueOf(x)).
                    setParameter("y", String.valueOf(y));
            HttpPost post = new HttpPost(uri.build());

            //Trying to execute the request and reading the content out of it
            HttpResponse response = client.execute(post);
            Scanner sc = new Scanner(response.getEntity().getContent());
            String json = sc.nextLine();

            return parseFromJson(json);

        } catch (IOException | NullPointerException | URISyntaxException ex) {
            throw new HTTP400StatusException("Could not get the expected"
                    + "response from the REST API", ex);
        }
    }

    @Override
    public  APIResponse currentCell(String mazeGuid) {
        //Building the method, and the URI
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            URIBuilder uri = new URIBuilder(BASE_URL + "/api/currentCell");
            uri.setParameter("mazeGuid", mazeGuid);
            HttpPost post = new HttpPost(uri.build());

            //Trying to execute the request and reading the content out of it
            HttpResponse response = client.execute(post);
            Scanner sc = new Scanner(response.getEntity().getContent());
            String json = sc.nextLine();

            return parseFromJson(json);

        } catch (IOException | NullPointerException | URISyntaxException ex) {
            //TO DO
            return null;
        }
    }

    private APIResponse parseFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, APIResponse.class);
    }

}
