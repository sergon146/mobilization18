package com.sergon146.mobilization18.ui.fragments.picture.picturelist;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.base.ResultTitle;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.core.utils.ViewUitl;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.di.base.Injectable;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.picture.picturelist.adapter.PictureListAdapter;
import com.sergon146.mobilization18.util.NetworkUtil;
import com.wang.avi.AVLoadingIndicatorView;

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
    implements PictureListView, Injectable {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_text)
    EditText searchText;
    @BindView(R.id.throbber)
    AVLoadingIndicatorView throbber;
    @BindView(R.id.connection_lost)
    View connectionLost;

    @Inject
    @InjectPresenter
    PictureListPresenter presenter;

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
        checkNetwork();
        return view;
    }

    private void checkNetwork() {
        if (NetworkUtil.isLostConnection(getContext())) {
            connectionLost();
        } else {
            connectionRestore();
        }
    }

    private void initRecycler() {
        int spanCount = getResources().getInteger(R.integer.list_column_count);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = adapter.getItemViewType(position);
                if (itemViewType == PictureListAdapter.THROBBER_VIEW_TYPE
                    || itemViewType == PictureListAdapter.TITLE_VIEW_TYPE) {
                    return layoutManager.getSpanCount();
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PictureListAdapter(pic -> getPresenter().openDetail(pic));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @OnClick(R.id.search)
    void onSearchClick() {
        String newKeyword = searchText.getText().toString();
        if (keyword.equals(newKeyword) && !adapter.isEmpty()) {
            return;
        } else {
            keyword = newKeyword;
        }

        if (!keyword.isEmpty()) {
            getPresenter().loadFirstPage(keyword);
        }

        ViewUitl.hideKeyboard(getContext(), getView());
    }

    @Override
    public String getLogName() {
        return "PhotoListFragment";
    }

    @Override
    public void connectionLost() {
        if (adapter.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            connectionLost.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            connectionLost.setVisibility(View.GONE);
            hideThrobber();
        }
    }

    @Override
    public void connectionRestore() {
        recyclerView.setVisibility(View.VISIBLE);
        connectionLost.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();

        if (!adapter.isEmpty()) {
            getPresenter().startPagination();
        }
    }

    @Override
    public void initShowPictures(List<Picture> pictures, ResultTitle resultTitle) {
        adapter.setItems(pictures, resultTitle);
    }

    @Override
    public void addPictures(List<Picture> pictures) {
        adapter.addItems(pictures);
    }

    @Override
    public void preparePagination() {
        getPresenter().preparePagination(recyclerView);
    }

    @Override
    public void showThrobber() {
        if (!NetworkUtil.isLostConnection(getContext())) {
            adapter.showThrobber();
        }
    }

    @Override
    public void hideThrobber() {
        adapter.hideThrobber();
    }

    @Override
    public void showMainThrobber() {
        throbber.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMainThrobber() {
        throbber.setVisibility(View.GONE);
    }
}
