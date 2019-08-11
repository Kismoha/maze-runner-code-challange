package mazerunner.restapiacces;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import mazerunner.exceptions.HTTP400StatusException;
import mazerunner.restapiresponse.APIResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/** The implementation of RestRequests interface which handles
 * the communication with the API.
 * The execution uses apache's HTTPClient.
 *
 * @author kismoha
 */
public class RestRequestsImpl implements RestRequests {

    /** This method handles the initial message which is used to create
     * the maze and get the starting cell and the maze ID.
     * The path is BASE_URL/api/init.
     * POST method.
     *
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
    @Override
    public APIResponse initMaze() throws HTTP400StatusException {
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpPost post = new HttpPost(BASE_URL + "/api/init");

            HttpResponse response = client.execute(post);
            Scanner sc = new Scanner(response.getEntity().getContent());
            String json = sc.nextLine();

            return parseFromJson(json);
        } catch (IOException ex) {
            throw new HTTP400StatusException("Could not get the expected"
                    + "response from the REST API", ex);
        }
    }

    /** This method handles a movement in the maze to a neighbouring cell.
     * The path is BASE_URL/api/move.
     * POST method.
     *
     * @param mazeGuid The ID of the maze which we want to move in.
     * @param direction The direction of the movement.
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
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

    /** This method handles a jump in the maze to a specified cell which we
     * already visited.
     * The path is BASE_URL/api/jump.
     * POST method.
     * 
     * @param mazeGuid The ID of the maze which we want to jump in.
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @return returns an APIResponse object parsed from the response json.
     * @throws HTTP400StatusException
     */
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

    /** This method handles querying of the current cell.
     * The path is BASE_URL/api/currentCell.
     * GET method.
     * 
     * @param mazeGuid The id of the maze where we want to get the current cell.
     * @return returns an APIResponse object parsed from the response json.
     */
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

    //Parses a response from the API into a APIResponse object via gson.
    private APIResponse parseFromJson(String json) {
        Gson gson = new Gson();
        try{
        return gson.fromJson(json, APIResponse.class);
        }catch(JsonSyntaxException e){
            System.out.println(json);
        }
        return gson.fromJson(json, APIResponse.class);
    }

}
