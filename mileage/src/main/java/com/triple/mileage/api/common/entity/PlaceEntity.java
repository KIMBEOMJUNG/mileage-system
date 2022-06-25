package com.triple.mileage.api.common.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.triple.mileage.common.validation.group.ModifyValidationGroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceEntity {

    @NotNull(groups = {ModifyValidationGroup.class })
    @Id
    private Long id;

    private String placeName;

    private String placeContext;

    // ==================

    private String reviewCount;

}
