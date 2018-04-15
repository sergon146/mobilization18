package com.sergon146.mobilization18.ui.fragments.photolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.photolist.adapter.PhotoAdapter;
import com.sergon146.mobilization18.ui.view.AutoFitGridLayoutManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class PhotoListFragment extends BaseMvpFragment<PhotoListPresenter>
        implements PhotoListView {

    private static final String SEARCH_TEXT = "SEARCH_TEXT";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_text)
    EditText searchText;

    @Inject
    @InjectPresenter
    PhotoListPresenter presenter;

    private PhotoAdapter adapter;

    public static PhotoListFragment getInstance() {
        return new PhotoListFragment();
    }

    @Override
    @ProvidePresenter
    protected PhotoListPresenter providePresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            searchText.setText(savedInstanceState.getString(SEARCH_TEXT, ""));
        }
        initRecycler();
        return view;
    }

    private void initRecycler() {
        adapter = new PhotoAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new AutoFitGridLayoutManager(getContext(), recyclerView));
    }

    @OnClick(R.id.search)
    void onSearchClick() {
        String keyword = searchText.getText().toString();
        if (!keyword.isEmpty()) {
            presenter.loadFirstPage(keyword);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_TEXT, searchText.getText().toString());
    }

    @Override
    public String getLogName() {
        return "PhotoListFragment";
    }

    @Override
    public void showPictures(List<Picture> pictures) {
        adapter.setItems(pictures);
    }

    @Override
    public void showEmptyMessages() {

    }
}
