package com.se.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComputeServiceImpl implements ComputeService{
    @Override
    public double irr(double[] income) {
        return irr(income, 0.1D);
    }
    @Override
    public double irr(double[] values, double guess) {
        int maxIterationCount = 20;
        double absoluteAccuracy = 1.0E-007D;

        double x0 = guess;

        int i = 0;
        while (i < maxIterationCount) {
            double fValue = 0.0D;
            double fDerivative = 0.0D;
            for (int k = 0; k < values.length; k++) {
                fValue += values[k] / Math.pow(1.0D + x0, k);
                fDerivative += -k * values[k] / Math.pow(1.0D + x0, k + 1);
            }
            double x1 = x0 - fValue / fDerivative;
            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }
            x0 = x1;
            i++;
        }
        return (0.0D / 0.0D);
    }
    @Override
    public ArrayList<ArrayList<Double>> Compute (ArrayList<Double> list) {
        ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Double> res2 = new ArrayList<Double>();
            for (int j = 0; j < 4; j++) {
                //收入，投资，运维，人员
                double s = 1 + list.get(i);
                double shouru = 480;
                double touzi = 200;
                double yunwei = 60;
                double renyuan = 20;
                if (j == 0) {
                    shouru = shouru * s;
                    double[] income = {shouru - touzi - yunwei - renyuan * 20, shouru * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20};
                    double ret = irr(income, 0.00001d);
                    res2.add(ret);
                }
                if (j == 1) {
                    touzi = touzi * s;
                    double[] income = {shouru - touzi - yunwei - renyuan * 20, shouru * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20};
                    double ret = irr(income, 0.00001d);
                    res2.add(ret);
                }
                if (j == 2) {
                    yunwei = yunwei * s;
                    double[] income = {shouru - touzi - yunwei - renyuan * 20, shouru * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20};
                    double ret = irr(income, 0.00001d);
                    res2.add(ret);
                }
                if (j == 3) {
                    renyuan = renyuan * s;
                    double[] income = {shouru - touzi - yunwei - renyuan * 20, shouru * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20, shouru * 1.04 * 1.04 * 1.04 * 1.04 * 1.04 - yunwei - renyuan * 20};
                    double ret = irr(income, 0.00001d);
                    res2.add(ret);
                }
            }
            res.add(res2);
        }
        return res;
    }

}
