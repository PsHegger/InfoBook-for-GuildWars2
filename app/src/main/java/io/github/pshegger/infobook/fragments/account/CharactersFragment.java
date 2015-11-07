package io.github.pshegger.infobook.fragments.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.pshegger.infobook.R;
import io.github.pshegger.infobook.adapters.CharacterListAdapter;
import io.github.pshegger.infobook.fragments.BaseInfoBookFragment;
import io.github.pshegger.infobook.model.CharacterData;
import io.github.pshegger.infobook.utils.OnRecyclerViewItemClickListener;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by pshegger on 2015.10.04..
 */
public class CharactersFragment extends BaseInfoBookFragment implements SwipeRefreshLayout.OnRefreshListener {
    List<CharacterData> mCharacters;
    SwipeRefreshLayout mRefreshLayout;
    RecyclerView mList;
    CharacterListAdapter mListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacters = new ArrayList<>();
        mListAdapter = new CharacterListAdapter(mCharacters, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CharacterData cd = mCharacters.get(position);

                Toast.makeText(getContext(), cd.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_character_list, container, false);

        mRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.characters_refresh);
        mRefreshLayout.setOnRefreshListener(this);

        mList = (RecyclerView) root.findViewById(R.id.characters_list);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mList.setAdapter(mListAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        refresh();
    }

    private void refresh() {
        getAPIService()
                .getAllCharacters()
                .enqueue(new Callback<List<CharacterData>>() {
                    @Override
                    public void onResponse(Response<List<CharacterData>> response, Retrofit retrofit) {
                        mCharacters.clear();
                        mCharacters.addAll(response.body());
                        Collections.sort(mCharacters);
                        mListAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

    @Override
    public String getName() {
        return "Characters";
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
