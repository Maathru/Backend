package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.parent.ChildBirthDto;
import com.maathru.backend.Domain.entity.parent.ChildBirth;
import com.maathru.backend.Domain.entity.parent.ChildDetail;
import com.maathru.backend.Domain.entity.parent.ObstetricComplication;

public class ChildBirthMapper {
    public void toChildBirth(ChildBirth childBirth, ChildBirthDto dto) {
        if (dto == null) {
            return;
        }

        childBirth.setBirthPlace(dto.getBirthPlace());
        childBirth.setDob(dto.getDob());
        childBirth.setDateReleased(dto.getDateReleased());
        childBirth.setDoneBy(dto.getDoneBy());
        childBirth.setResultOfPregnancy(dto.getResultOfPregnancy());
        childBirth.setStatusOfPregnancy(dto.getStatusOfPregnancy());
    }

    public void toObstetricComplication(ObstetricComplication obstetricComplication, ChildBirthDto dto) {
        if (dto == null) {
            return;
        }

        obstetricComplication.setCrackedAround(dto.isCrackedAround());
        obstetricComplication.setPostpartumBleeding(dto.isPostpartumBleeding());
        obstetricComplication.setTrappedAura(dto.isTrappedAura());
        obstetricComplication.setAnyCutsAround(dto.isAnyCutsAround());
        obstetricComplication.setLongDelivery(dto.isLongDelivery());
        obstetricComplication.setOtherComplications(dto.isOtherComplications());
        obstetricComplication.setVitaminADose(dto.isVitaminADose());
        obstetricComplication.setRubellaVaccine(dto.isRubellaVaccine());
    }

    public void toChildDetail(ChildDetail childDetail, ChildBirthDto dto) {
        if (dto == null) {
            return;
        }

        childDetail.setSex(dto.getSex());
        childDetail.setBirthWeight(dto.getBirthWeight());
        childDetail.setPrematureBirth(dto.isPrematureBirth());
        childDetail.setComplicationsAtBirth(dto.isComplicationsAtBirth());

        childDetail.setMotherDead(dto.isMotherDead());
        childDetail.setMotherDeadDate(dto.getMotherDeadDate());
        childDetail.setMotherDeadCause(dto.getMotherDeadCause());
        childDetail.setInvestigated(dto.isInvestigated());
    }

    public ChildBirthDto toChildBirthDto(ChildBirth childBirth, ObstetricComplication obstetricComplication, ChildDetail childDetail) {
        ChildBirthDto childBirthDto = new ChildBirthDto();

        childBirthDto.setBirthPlace(childBirth.getBirthPlace());
        childBirthDto.setDob(childBirth.getDob());
        childBirthDto.setDateReleased(childBirth.getDateReleased());
        childBirthDto.setDoneBy(childBirth.getDoneBy());
        childBirthDto.setResultOfPregnancy(childBirth.getResultOfPregnancy());
        childBirthDto.setStatusOfPregnancy(childBirth.getStatusOfPregnancy());

        childBirthDto.setCrackedAround(obstetricComplication.isCrackedAround());
        childBirthDto.setPostpartumBleeding(obstetricComplication.isPostpartumBleeding());
        childBirthDto.setTrappedAura(obstetricComplication.isTrappedAura());
        childBirthDto.setAnyCutsAround(obstetricComplication.isAnyCutsAround());
        childBirthDto.setLongDelivery(obstetricComplication.isLongDelivery());
        childBirthDto.setOtherComplications(obstetricComplication.isOtherComplications());
        childBirthDto.setVitaminADose(obstetricComplication.isVitaminADose());
        childBirthDto.setRubellaVaccine(obstetricComplication.isRubellaVaccine());

        childBirthDto.setSex(childDetail.getSex());
        childBirthDto.setBirthWeight(childDetail.getBirthWeight());
        childBirthDto.setPrematureBirth(childDetail.isPrematureBirth());
        childBirthDto.setComplicationsAtBirth(childDetail.isComplicationsAtBirth());

        childBirthDto.setMotherDead(childDetail.isMotherDead());
        childBirthDto.setMotherDeadDate(childDetail.getMotherDeadDate());
        childBirthDto.setMotherDeadCause(childDetail.getMotherDeadCause());
        childBirthDto.setInvestigated(childDetail.isInvestigated());

        return childBirthDto;
    }
}
