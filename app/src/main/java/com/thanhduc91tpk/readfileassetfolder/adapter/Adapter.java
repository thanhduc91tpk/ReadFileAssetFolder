package com.thanhduc91tpk.readfileassetfolder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thanhduc91tpk.readfileassetfolder.R;
import com.thanhduc91tpk.readfileassetfolder.model.ItemClass;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class Adapter extends ExpandableRecyclerViewAdapter<Adapter.HeaderViewHolder, Adapter.ItemViewHolder> {

    public Adapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public HeaderViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,parent,false));
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.onBinData(group.getItems().get(childIndex).toString());
    }

    @Override
    public void onBindGroupViewHolder(HeaderViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.onBinData(group.getTitle());
    }

    public class HeaderViewHolder extends GroupViewHolder{
        TextView txtNameHeader;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            txtNameHeader = itemView.findViewById(R.id.txt_name_header);
        }

        public void onBinData(String str){
            txtNameHeader.setText(str);
        }
    }

    public class ItemViewHolder extends ChildViewHolder{
        TextView txtNameContent;
        public ItemViewHolder(View itemView) {
            super(itemView);
            txtNameContent = itemView.findViewById(R.id.txtContent);
        }

        public void onBinData(String str){
            txtNameContent.setText(str);
        }
    }
}
