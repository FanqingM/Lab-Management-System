package com.se.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface ComputeService {
    public double irr(double[] income);

    public double irr(double[] values, double guess);


    public ArrayList<ArrayList<Double>> Compute (ArrayList<Double> list);

//    public static void main(String[] args) {
//        ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
//        ArrayList<Double> list = new ArrayList<Double>();
//        list.add(0.02);
//        list.add(0.04);
//        res = Compute(list);
////        double[] income = {-100,39,59,55,20};
////        double ret = irr(income,0.00001d) ;
//        System.out.println(res);
//    }
}
