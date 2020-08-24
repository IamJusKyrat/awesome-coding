package com.turing.algorithm.dynamicprogramming;

import java.util.*;

public class PatientAppointment {
    private final Scanner scanner = new Scanner(System.in);

    public void calculateAppointment(){
        String[] firstInput = scanner.nextLine().split(" ");
        //TODO: apply validations on count
        int doctorCount = Integer.parseInt(firstInput[0]);
        int patientCount = Integer.parseInt(firstInput[1]);
        int[][] efforts = new int[doctorCount][patientCount];

        for(int i = 0; i < doctorCount; i++){
            efforts[i] = getEffortInputs(patientCount);
        }

        for(int i = 0; i < doctorCount; i++){
            System.out.println(Arrays.toString(efforts[i]));
        }

        //Find out smallest net sum sequences and pair them one after the other
    }

    private int[] getEffortInputs(int inputCount) {
        String[] input = scanner.nextLine().split(" ");
        if(input.length < inputCount){
            System.out.println("Need " + inputCount + " inputs.");
            getEffortInputs(inputCount);
        }
        int[] effort = new int[inputCount];
        for(int i=0; i< inputCount; i++) {
            effort[i] = Integer.parseInt(input[i]);
        }
        return effort;
    }
}
