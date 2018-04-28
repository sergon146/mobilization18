package com.sergon146.mobilization18.ui.fragments.picture.picturedetail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.business.model.picture.PicturesList;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.base.BaseMvpFragment;
import com.sergon146.mobilization18.ui.fragments.picture.picturedetail.adapter.PicturePageAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PictureDetailFragment extends BaseMvpFragment<PictureDetailPresenter>
        implements PictureDetailView {
    private static final String PICTURES_DTO_ARG = "PICTURES_DTO_ARG";

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.arrow_left)
    View arrowLeft;
    @BindView(R.id.arrow_right)
    View arrowRight;

    @Inject
    @InjectPresenter
    PictureDetailPresenter presenter;

    private int currentPosition;
    private List<Picture> pictures;

    public static PictureDetailFragment getInstance(PicturesList picturesDto) {
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
            PicturesList dto = Parcels.unwrap(getArguments().getParcelable(PICTURES_DTO_ARG));
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

        setupPicturePager();
        pageChanged(currentPosition);
        return view;
    }

    private void setupPicturePager() {
        PicturePageAdapter adapter = new PicturePageAdapter(pictures);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentPosition);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void pageChanged(int position) {
        currentPosition = position;
        setupControls();
    }

    private void setupControls() {
        if (currentPosition == 0) {
            arrowLeft.setVisibility(View.GONE);
        } else {
            arrowLeft.setVisibility(View.VISIBLE);
        }

        if (currentPosition == pictures.size() - 1) {
            arrowRight.setVisibility(View.GONE);
        } else {
            arrowRight.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.arrow_back)
    public void onBackClick() {
        presenter.navigateBack();
    }

    @OnClick(R.id.arrow_left)
    public void onArrowLeftClick() {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }

    @OnClick(R.id.arrow_right)
    public void onArrowRightClick() {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }

    @Override
    public String getLogName() {
        return "PhotoDetailFragment";
    }
}
