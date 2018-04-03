/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package extensionit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bdapps.activity.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.SimpleViewHolder> {
    private static final int COUNT = 100;

    private final List<Integer> items;
    private boolean isGrid = true;

    public void setIsGrid(boolean isGrid) {
        this.isGrid = isGrid;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public ItemAdapter( ) {
        items = new ArrayList<Integer>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            items.add(i, i);
            notifyItemInserted(i);
        }
    }



    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        holder.title.setText(String.valueOf((position + 1)));


    }

    @Override
    public int getItemViewType(int position) {
        if(isGrid)
            return 1;
        else
            return 2;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
