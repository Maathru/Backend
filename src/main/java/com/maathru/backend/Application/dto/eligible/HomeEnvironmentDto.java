package com.maathru.backend.Application.dto.eligible;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeEnvironmentDto {
    @NotNull(message = "Man's Clean safe water field value cannot be empty")
    private boolean manSafeWater;
    @NotNull(message = "Man's Toilet facilities field value cannot be empty")
    private boolean manToilet;
    @NotNull(message = "Man's Burying facilities to properly field value cannot be empty")
    private boolean manGarbage;
    @NotNull(message = "Man's hazardous situations in the environment field value cannot be empty")
    private boolean manHazardous;
    @NotNull(message = "Man's Chemicals field value cannot be empty")
    private boolean manChemicals;
    @NotNull(message = "Man's Radioactive substances such as ext field value cannot be empty")
    private boolean manRadioactive;
    @NotNull(message = "Man's Extreme heat conditions field value cannot be empty")
    private boolean manExtremeHeat;
    @NotNull(message = "Man's Smoke from cooking or other things field value cannot be empty")
    private boolean manSmokeCooking;
    @NotNull(message = "Man's live/work in a noisy environment field value cannot be empty")
    private boolean manNoisy;
    @NotNull(message = "Man's financial management skills field value cannot be empty")
    private boolean manSaving;

    @NotNull(message = "Woman's Clean safe water field value cannot be empty")
    private boolean womanSafeWater;
    @NotNull(message = "Woman's Toilet facilities field value cannot be empty")
    private boolean womanToilet;
    @NotNull(message = "Woman's Burying facilities to properly field value cannot be empty")
    private boolean womanGarbage;
    @NotNull(message = "Woman's hazardous situations in the environment field value cannot be empty")
    private boolean womanHazardous;
    @NotNull(message = "Woman's Chemicals field value cannot be empty")
    private boolean womanChemicals;
    @NotNull(message = "Woman's Radioactive substances such as ext field value cannot be empty")
    private boolean womanRadioactive;
    @NotNull(message = "Woman's Extreme heat conditions field value cannot be empty")
    private boolean womanExtremeHeat;
    @NotNull(message = "Woman's Smoke from cooking or other things field value cannot be empty")
    private boolean womanSmokeCooking;
    @NotNull(message = "Woman's live/work in a noisy environment field value cannot be empty")
    private boolean womanNoisy;
    @NotNull(message = "Woman's financial management skills field value cannot be empty")
    private boolean womanSaving;

    private String waterDetails;
    private String toiletDetails;
    private String garbageDetails;
    private String hazardousDetails;
    private String chemicalsDetails;
    private String radioactiveDetails;
    private String extremeHeatDetails;
    private String smokeCookingDetails;
    private String noisyDetails;
    private String savingDetails;
}
