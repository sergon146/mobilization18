package com.sergon146.core.mappers;

import com.sergon146.business.model.picture.Picture;
import com.sergon146.business.model.picture.PicturesList;
import com.sergon146.core.api.entities.PictureDto;
import com.sergon146.core.api.entities.PictureResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 21.04.2018
 */

public class PictureListMapper implements PairMapper<PictureResponse, PicturesList> {
    private PictureMapper pictureMapper = new PictureMapper();

    @Override
    public PictureResponse to(PicturesList destination) {
        PictureResponse response = new PictureResponse();
        response.setTotalHits(destination.getTotalHits());
        List<PictureDto> pictures = new ArrayList<>();
        for (Picture picture : destination.getPictures()) {
            pictures.add(pictureMapper.to(picture));
        }
        response.setPictures(pictures);
        return response;
    }

    @Override
    public PicturesList from(PictureResponse source) {
        PicturesList list = new PicturesList();
        list.setTotalHits(source.getTotalHits());
        List<Picture> pictures = new ArrayList<>();
        for (PictureDto pictureDto : source.getPictures()) {
            pictures.add(pictureMapper.from(pictureDto));
        }
        list.setPictures(pictures);
        return list;
    }
}
