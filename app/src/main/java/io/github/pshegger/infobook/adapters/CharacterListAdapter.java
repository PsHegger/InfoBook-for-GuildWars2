package io.github.pshegger.infobook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.pshegger.infobook.R;
import io.github.pshegger.infobook.model.CharacterData;
import io.github.pshegger.infobook.utils.IconLoadHelper;
import io.github.pshegger.infobook.utils.OnRecyclerViewItemClickListener;

/**
 * Created by pshegger on 2015.10.13..
 */
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {
    private List<CharacterData> mCharacters;
    private OnRecyclerViewItemClickListener mListener;

    public CharacterListAdapter(List<CharacterData> characters, OnRecyclerViewItemClickListener listener) {
        mCharacters = characters;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_character_row, parent, false);

        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public CharacterData getItem(int position) {
        return mCharacters.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLogo;
        private TextView tvName, tvProfession, tvLevel;
        private View root;

        private OnRecyclerViewItemClickListener mListener;
        private int mPosition;

        public ViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);

            root = itemView;
            mListener = listener;

            ivLogo = (ImageView) itemView.findViewById(R.id.profession_logo);
            tvName = (TextView) itemView.findViewById(R.id.character_name);
            tvProfession = (TextView) itemView.findViewById(R.id.character_profession);
            tvLevel = (TextView) itemView.findViewById(R.id.character_level);

            if (listener != null) {
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(mPosition);
                    }
                });
            }
        }

        public void bind(CharacterData ch, int position) {
            mPosition = position;

            tvName.setText(ch.getName());
            tvProfession.setText(ch.getRace().name());
            tvLevel.setText(String.valueOf(ch.getLevel()));

            IconLoadHelper.loadIcon(
                    IconLoadHelper.IconType.File,
                    ch.getProfession().getIconId(true),
                    ivLogo
            );
        }
    }
}
