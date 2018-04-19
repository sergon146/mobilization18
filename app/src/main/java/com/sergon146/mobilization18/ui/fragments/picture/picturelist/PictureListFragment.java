package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.picture.picturelist.adapter.PictureListAdapter;
import com.sergon146.mobilization18.ui.view.AutoFitGridLayoutManager;
import com.sergon146.mobilization18.utils.ViewUitl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class PictureListFragment extends BaseMvpFragment<PictureListPresenter>
        implements PictureListView {

    private static final String SEARCH_TEXT = "SEARCH_TEXT";
    private static final String RECYCLER_STATE = "RECYCLER_STATE";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.search_result)
    TextView searchResult;
    @BindView(R.id.throbber)
    ProgressBar throbber;
    @BindView(R.id.pagination_throbber)
    ProgressBar paginationThrobber;

    @Inject
    @InjectPresenter
    PictureListPresenter presenter;

    private Parcelable listState;
    private PictureListAdapter adapter;
    private String keyword = "";

    public static PictureListFragment getInstance() {
        return new PictureListFragment();
    }

    @Override
    @ProvidePresenter
    protected PictureListPresenter providePresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_list, container, false);
        ButterKnife.bind(this, view);
        initRecycler();

        searchText.setText(keyword);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void initRecycler() {
        adapter = new PictureListAdapter(pic -> getPresenter().openDetail(pic));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new AutoFitGridLayoutManager(getContext(), recyclerView));
    }

    @OnClick(R.id.search)
    void onSearchClick() {
        String newKeyword = searchText.getText().toString();
        if (keyword.equals(newKeyword)) {
            return;
        } else {
            keyword = newKeyword;
        }

        ViewUitl.hideKeyboard(getContext(), getView());

        if (!keyword.isEmpty()) {
            presenter.loadFirstPage(keyword);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }

        keyword = savedInstanceState.getString(SEARCH_TEXT);
        listState = savedInstanceState.getParcelable(RECYCLER_STATE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_TEXT, searchText.getText().toString());
        listState = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_STATE, listState);
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
    public void showSearchResultCount(int count) {
        searchResult.setVisibility(View.VISIBLE);
        searchResult.setText(getResources().getString(R.string.search_result, keyword,
                getResources().getQuantityString(R.plurals.hours_count_to_watch, count, count)));
    }

    @Override
    public void hideSearchResultCount() {
        searchResult.setVisibility(View.GONE);
    }

    @Override
    public void showThrobber() {
        throbber.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideThrobber() {
        throbber.setVisibility(View.GONE);
    }
}
