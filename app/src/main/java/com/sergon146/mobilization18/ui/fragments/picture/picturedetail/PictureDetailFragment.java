package com.sergon146.mobilization18.ui.fragments.picture.picturedetail;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.core.dal.dto.PicturesDto;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.picture.picturedetail.adapter.PicturePageAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PictureDetailFragment extends BaseMvpFragment<PictureDetailPresenter>
        implements PictureDetailView {
    private static final String PICTURES_DTO_ARG = "PICTURES_DTO_ARG";

    @Inject
    @InjectPresenter
    PictureDetailPresenter presenter;

    @BindView(R.id.pager)
    ViewPager pager;

    private int currentPosition;
    private List<Picture> pictures;

    public static PictureDetailFragment getInstance(PicturesDto picturesDto) {
        PictureDetailFragment fragment = new PictureDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(PICTURES_DTO_ARG, Parcels.wrap(picturesDto));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @ProvidePresenter
    protected PictureDetailPresenter providePresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PicturesDto dto = Parcels.unwrap(getArguments().getParcelable(PICTURES_DTO_ARG));
            if (dto == null) {
                return;
            }

            currentPosition = dto.getPosition();
            pictures = dto.getPictures();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_detail, container, false);
        ButterKnife.bind(this, view);

        PicturePageAdapter adapter = new PicturePageAdapter(pictures);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentPosition);
        return view;
    }

    @Override
    public String getLogName() {
        return "PhotoDetailFragment";
    }
}
