package com.deloitte.elrr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class LearnerSnapshotDTO {

	long totalLearnerProfiles;
	long totalLearners;
	long totalCourses;
	long totalCompetencies;
}
