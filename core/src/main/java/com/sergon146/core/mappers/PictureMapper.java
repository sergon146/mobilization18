package com.sergon146.core.mappers;

import com.sergon146.business.model.picture.Picture;
import com.sergon146.core.api.entities.PictureDto;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 21.04.2018
 */

public class PictureMapper implements PairMapper<PictureDto, Picture>{
    @Override
    public PictureDto to(Picture destination) {
        PictureDto dto = new PictureDto();
        dto.setId(destination.getId());
        dto.setImageHeight(destination.getImageHeight());
        dto.setImageWidth(destination.getImageWidth());
        dto.setImageSize(destination.getImageSize());
        dto.setLargeImageURL(destination.getLargeImageURL());
        dto.setPreviewURL(destination.getPreviewURL());
        dto.setPreviewHeight(destination.getPreviewHeight());
        dto.setPreviewWidth(destination.getPreviewWidth());
        dto.setWebformatURL(destination.getWebformatURL());
        dto.setWebformatHeight(destination.getWebformatHeight());
        dto.setWebformatWidth(destination.getWebformatWidth());
        return dto;
    }

    @Override
    public Picture from(PictureDto source) {
        Picture picture = new Picture();
        picture.setId(source.getId());
        picture.setImageHeight(source.getImageHeight());
        picture.setImageWidth(source.getImageWidth());
        picture.setImageSize(source.getImageSize());
        picture.setLargeImageURL(source.getLargeImageURL());
        picture.setPreviewURL(source.getPreviewURL());
        picture.setPreviewHeight(source.getPreviewHeight());
        picture.setPreviewWidth(source.getPreviewWidth());
        picture.setWebformatURL(source.getWebformatURL());
        picture.setWebformatHeight(source.getWebformatHeight());
        picture.setWebformatWidth(source.getWebformatWidth());
        return picture;
    }
}
