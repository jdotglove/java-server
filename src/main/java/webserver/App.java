package webserver;

import webserver.lib.Router;

import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Test functional server library.
 */
public class App {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/album/", (req) -> Router.handleAlbumApi(req));
        server.createContext("/artist/", (req) -> Router.handleArtistApi(req));
        server.createContext("/discovery/", (req) -> Router.handleDiscoveryApi(req));
        server.createContext("/playlist/", (req) -> Router.handlePlaylistApi(req));
        server.createContext("/recommendation/", (req) -> Router.handleRecommendationApi(req));
        server.createContext("/track/", (req) -> Router.handleTrackApi(req));
        server.createContext("/user/", (req) -> Router.handleUserApi(req));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port " + port);
    }

    // public static void main(String[] args) throws IOException {
    //     Server audionestServer = new Server(8080);

    //     // Add Album Routes
    //     audionestServer.addRoute(GET, "/album/{id}/tracks", (req) -> Album.getAlbumTracks(req));
    //     audionestServer.addRoute(GET, "/album/{id}/", (req) -> Album.getAlbum(req));

    //     // Add Artist Routes
    //     audionestServer.addRoute(POST, "/artist/search", (req) -> Artist.searchForArtist(req));
    //     audionestServer.addRoute(GET, "/artist/{id}", (req) -> Artist.getArtist(req));

    //     // Add Discovery Routes
    //     audionestServer.addRoute(GET, "/discovery/new-releases", (req) -> Discovery.getNewReleases(req));
    //     audionestServer.addRoute(GET, "/discovery/categories", (req) -> Discovery.getBrowsingCategories(req));
    //     audionestServer.addRoute(GET, "/discovery/{category}/playlists", (req) -> Discovery.getCategoryPlaylists(req));

    //     // Add Playlist Routes
    //     audionestServer.addRoute(GET, "/playlist/{id}/tracks", (req) -> Playlist.getPlaylistTracks(req));
    //     audionestServer.addRoute(GET, "/playlist/{id}", (req) -> Playlist.getPlaylist(req));

    //     // Add Recommendation Routes
    //     audionestServer.addRoute(POST, "/recommendations", (req) -> Recommendation.generateRecommendations(req));
    //     audionestServer.addRoute(GET, "/recommendations/seed-genres", (req) -> Recommendation.getSeedGenres(req));

    //     // Add Track Routes
    //     audionestServer.addRoute(POST, "/track/search", (req) -> Track.searchForTrack(req));
    //     audionestServer.addRoute(GET, "/track/{id}/audio-features", (req) -> Track.getTrackAudioFeatures(req));
    //     audionestServer.addRoute(GET, "/track/{id}", (req) -> Track.getTrack(req));
    //     audionestServer.addRoute(GET, "/tracks", (req) -> Track.getSelectedTracks(req));

    //     // Add User Routes
    //     audionestServer.addRoute(GET, "/user/{id}/playlists", (req) -> User.getUserPlaylists(req));
    //     audionestServer.addRoute(GET, "/user/{id}/playback-state", (req) -> User.getCurrentTrackBreakdown(req));
    //     audionestServer.addRoute(GET, "/user/{id}/top-artists", (req) -> User.getUserTopArtists(req));
    //     audionestServer.addRoute(GET, "/user/{id}/top-tracks", (req) -> User.getUserTopTracks(req));
    //     audionestServer.addRoute(POST, "/user/{id}/queue", (req) -> User.addToUserQueue(req));
    //     audionestServer.addRoute(POST, "/user/{id}/playlist", (req) -> User.createUserPlaylist(req));
    //     audionestServer.addRoute(POST, "/user/login", (req) -> User.loginUser(req));


    //     audionestServer.start();
    // }
}
