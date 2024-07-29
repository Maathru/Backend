package com.maathru.backend.Domain.mapper;

import com.maathru.backend.Application.dto.eligible.ParentHabitDto;
import com.maathru.backend.Domain.entity.eligible.ParentHabit;

public class ParentHabitMapper implements Mapper<ParentHabit, ParentHabitDto> {
    public ParentHabit toEntity(ParentHabit parentHabit, ParentHabitDto dto) {
        if (dto == null) {
            return null;
        }

        parentHabit.setManCigarettes(dto.isManCigarettes());
        parentHabit.setManAlcohol(dto.isManAlcohol());
        parentHabit.setManDrugs(dto.isManDrugs());
        parentHabit.setManSmoke(dto.isManSmoke());
        parentHabit.setManViolent(dto.isManViolent());
        parentHabit.setManRituals(dto.isManRituals());
        parentHabit.setManExercise(dto.isManExercise());
        parentHabit.setManHobbies(dto.isManHobbies());

        parentHabit.setWomanCigarettes(dto.isWomanCigarettes());
        parentHabit.setWomanAlcohol(dto.isWomanAlcohol());
        parentHabit.setWomanDrugs(dto.isWomanDrugs());
        parentHabit.setWomanSmoke(dto.isWomanSmoke());
        parentHabit.setWomanViolent(dto.isWomanViolent());
        parentHabit.setWomanRituals(dto.isWomanRituals());
        parentHabit.setWomanExercise(dto.isWomanExercise());
        parentHabit.setWomanHobbies(dto.isWomanHobbies());

        parentHabit.setCigarettesDetails(dto.getCigarettesDetails());
        parentHabit.setAlcoholDetails(dto.getAlcoholDetails());
        parentHabit.setDrugsDetails(dto.getDrugsDetails());
        parentHabit.setSmokeDetails(dto.getSmokeDetails());
        parentHabit.setViolentDetails(dto.getViolentDetails());
        parentHabit.setRitualsDetails(dto.getRitualsDetails());
        parentHabit.setExerciseDetails(dto.getExerciseDetails());
        parentHabit.setHobbiesDetails(dto.getHobbiesDetails());

        return parentHabit;
    }
}
