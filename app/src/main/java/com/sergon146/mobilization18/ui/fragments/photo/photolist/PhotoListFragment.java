package com.sergon146.mobilization18.ui.fragments.photo.photolist;

import android.os.Bundle;
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
import com.sergon146.mobilization18.navigation.MainRouter;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.photo.photolist.adapter.PhotoAdapter;
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
public class PhotoListFragment extends BaseMvpFragment<PhotoListPresenter>
        implements PhotoListView {

    private static final String SEARCH_TEXT = "SEARCH_TEXT";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.search_result)
    TextView searchResult;
    @BindView(R.id.throbber)
    ProgressBar throbber;

    @Inject
    @InjectPresenter
    PhotoListPresenter presenter;
    @Inject
    MainRouter router;

    private PhotoAdapter adapter;
    private String keyword = "";

    public static PhotoListFragment getInstance() {
        return new PhotoListFragment();
    }

    @Override
    @ProvidePresenter
    protected PhotoListPresenter providePresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            keyword = savedInstanceState.getString(SEARCH_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);
        initRecycler();

        searchText.setText(keyword);
        return view;
    }

    private void initRecycler() {
        adapter = new PhotoAdapter(pos -> router.showDetailScreen(pos));
        recyclerView.setAdapter(adapter);
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
    public void showSearchResultTitle(int count) {
        searchResult.setVisibility(View.VISIBLE);
        searchResult.setText(getResources().getString(R.string.search_result, keyword,
                getResources().getQuantityString(R.plurals.hours_count_to_watch, count, count)));
    }

    @Override
    public void hideSearchResultTitle() {
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
