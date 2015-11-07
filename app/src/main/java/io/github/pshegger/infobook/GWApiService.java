package io.github.pshegger.infobook;

import java.util.List;

import io.github.pshegger.infobook.model.CharacterData;
import io.github.pshegger.infobook.model.FileData;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by pshegger on 2015.10.04..
 */
public interface GWApiService {

    @GET("characters")
    Call<List<String>> getCharactersList();

    @GET("characters?page=0")
    Call<List<CharacterData>> getAllCharacters();

    @GET("files")
    Call<List<String>> getAvailableItems();

    @GET("files/{id}")
    Call<FileData> getItem(@Path("id") String id);

    @GET("files")
    Call<List<FileData>> getItems(@Query("ids") String ids);
}
