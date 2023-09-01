package webserver.lib;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import webserver.lib.http.HttpHandler;
import webserver.lib.runners.Album;
import webserver.lib.runners.Artist;
import webserver.lib.runners.Discovery;
import webserver.lib.runners.Playlist;
import webserver.lib.runners.Recommendation;
import webserver.lib.runners.Track;
import webserver.lib.runners.User;
import static webserver.lib.helpers.HttpMethod.GET;
import static webserver.lib.helpers.HttpMethod.POST;

public class Router {
    public static void handleAlbumApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/album/(\\w+)/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(2);
                final String albumId = matcher.group(1);

                if (endpoint.equals("tracks")) {
                   Album.getAlbumTracks(exchange, albumId);
                }
            } else {
                Pattern pattern2 = Pattern.compile("/album/(\\w+)");
                matcher = pattern2.matcher(path);

                if (matcher.find()) {
                    final String albumId = matcher.group(1);

                    Album.getAlbum(exchange, albumId);
                }
            }

            HttpHandler.sendResponse(exchange, 404, "Album GET Route not found");
        }
        HttpHandler.sendResponse(exchange, 404, "Album route method not supported");
    }

    public static void handleArtistApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/artist/(\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                final String artistId = matcher.group(1);

                Artist.getArtist(exchange, artistId);
            }

            HttpHandler.sendResponse(exchange, 404, "GET Route not found");
        } else if (method.equals(POST.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(1);
                if (endpoint.equals("search")) {
                    Artist.searchForArtist(exchange);
                }
            }
            HttpHandler.sendResponse(exchange, 404, "POST Route not found");
        }
        HttpHandler.sendResponse(exchange, 404, "Artist route method not supported");
    }

    public static void handleDiscoveryApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/discovery/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(2);

                if (endpoint.equals("new-releases")) {
                   Discovery.getNewReleases(exchange);
                } else if (endpoint.equals("categories")) {
                    Discovery.getBrowsingCategories(exchange);
                }
            } else {
                Pattern pattern2 = Pattern.compile("/discovery/(\\w+)/(\\w+)");
                matcher = pattern2.matcher(path);

                if (matcher.find()) {
                    final String category = matcher.group(2);
                    String endpoint = matcher.group(2);
                    if (endpoint.equals("playlists")) {
                        Discovery.getCategoryPlaylists(exchange, category);
                    }
                   // Playlist.getPlaylist(exchange);
                }
            }

            HttpHandler.sendResponse(exchange, 404, "GET Route not found");
        }
        HttpHandler.sendResponse(exchange, 404, "Discovery route not supported");
    }

    public static void handlePlaylistApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/playlist/(\\w+)/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                final String playlistId = matcher.group(1);
                String endpoint = matcher.group(2);

                if (endpoint.equals("tracks")) {
                   Playlist.getPlaylistTracks(exchange, playlistId);
                }
            } else {
                Pattern pattern2 = Pattern.compile("/track/(\\w+)");
                matcher = pattern2.matcher(path);

                if (matcher.find()) {
                    final String playlistId = matcher.group(1);

                   Playlist.getPlaylist(exchange, playlistId);
                }
            }

            HttpHandler.sendResponse(exchange, 404, "GET Route not found");
        }
        HttpHandler.sendResponse(exchange, 404, "Playlist route not supported");
    }

    public static void handleRecommendationApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/recommendation/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(1);

                if (endpoint.equals("seed-genres")) {
                   Recommendation.getSeedGenres(exchange);
                }
            }

            HttpHandler.sendResponse(exchange, 404, "GET Route not found");
        } else if (method.equals(POST.toString())) {
            if (path.equals("/recommendation")) {
                // Recommendation.generateRecommendations(exchange);
            }
        }
        HttpHandler.sendResponse(exchange, 404, "Recommendation route not supported");
    }

    public static void handleTrackApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/track/(\\w+)/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                final String trackId = matcher.group(1);
                String endpoint = matcher.group(2);

                if (endpoint.equals("audio-features")) {
                    Track.getTrackAudioFeatures(exchange, trackId);
                }
            } else {
                Pattern pattern2 = Pattern.compile("/track/(\\w+)");
                matcher = pattern2.matcher(path);

                if (matcher.find()) {
                    final String trackId = matcher.group(1);

                    Track.getTrack(exchange, trackId);
                }
            }

            if (path.equals("/track")) {
               // Track.getSelectedTracks(exchange);
            }

            HttpHandler.sendResponse(exchange, 404, "GET Route not found");
        } else if (method.equals(POST.toString())) {
            if (matcher.find()) {
                Pattern pattern2 = Pattern.compile("/track/(\\w+)");
                matcher = pattern2.matcher(path);
                if (matcher.find()) {
                    String endpoint = matcher.group(1);

                    if (endpoint.equals("search")) {
                        // Track.searchForTrack(exchange);
                    } else {
                        HttpHandler.sendResponse(exchange, 404, "POST Route not found");
                    }
                }
            }
        }
        HttpHandler.sendResponse(exchange, 404, "Track route not supported");
    }

    public static void handleUserApi(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Pattern pattern1 = Pattern.compile("/user/(\\w+)/(\\w+\\-?\\w+)");
        Matcher matcher = pattern1.matcher(path);

        if (method.equals(GET.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(2);

                if (endpoint.equals("playlists")) {
                    User.getUserPlaylists(exchange);
                } else if (endpoint.equals("playback-state")) {
                    User.getCurrentTrackBreakdown(exchange);
                } else if (endpoint.equals("top-artists")) {
                    User.getUserTopArtists(exchange);
                } else if (endpoint.equals("top-tracks")) {
                    User.getUserTopTracks(exchange);
                }
            } else {
                HttpHandler.sendResponse(exchange, 404, "GET Route not found");
            }
        } else if (method.equals(POST.toString())) {
            if (matcher.find()) {
                String endpoint = matcher.group(2);
                if (endpoint.equals("queue")) {
                  //  User.addToUserQueue(exchange);
                } else if (endpoint.equals("playlist")) {
                  //  User.createUserPlaylist(exchange);
                }
            } else {
                Pattern pattern2 = Pattern.compile("/user/(\\w+)");
                matcher = pattern2.matcher(path);
                if (matcher.find()) {
                    String endpoint = matcher.group(1);

                    if (endpoint.equals("login")) {
                        User.loginUser(exchange);
                    } else {
                        HttpHandler.sendResponse(exchange, 404, "POST Route not found");
                    }
                }
            }
        }
        HttpHandler.sendResponse(exchange, 404, "User route not supported");
    }
}
