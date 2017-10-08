package com.demo.services.HelpData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class SubmissionData {
    Map<Integer, String> blocks;

    Map<Integer, List<Integer>> graph;
}
