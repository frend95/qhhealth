package com.hfkd.qhhealth.health.model;

import com.hfkd.qhhealth.common.constant.ConstEnum;
import com.hfkd.qhhealth.common.constant.ConstVal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 体脂秤数据标尺
 * @author hexq
 * @date 2018/7/16 10:05
 */
public class HealthMeasureRule implements Serializable{

    /**身体质量指数*/
    private String bmi = ConstEnum.SCALES_NORMAL.ename();
    /**体脂率*/
    private String bfr = ConstEnum.SCALES_NORMAL.ename();
    /**基础代谢率*/
    private String bmr = ConstEnum.SCALES_NORMAL.ename();
    /**肌肉率*/
    private String mr = ConstEnum.SCALES_NORMAL.ename();
    /**水含量*/
    private String wr = ConstEnum.SCALES_NORMAL.ename();
    /**内脏脂肪指数*/
    private String uvi = ConstEnum.SCALES_NORMAL.ename();
    /**骨骼质量*/
    private String bq = ConstEnum.SCALES_NORMAL.ename();
    /**蛋白质*/
    private String pr = ConstEnum.SCALES_NORMAL.ename();

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBfr() {
        return bfr;
    }

    public void setBfr(String bfr) {
        this.bfr = bfr;
    }

    public String getBmr() {
        return bmr;
    }

    public void setBmr(String bmr) {
        this.bmr = bmr;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getWr() {
        return wr;
    }

    public void setWr(String wr) {
        this.wr = wr;
    }

    public String getUvi() {
        return uvi;
    }

    public void setUvi(String uvi) {
        this.uvi = uvi;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public HealthMeasureRule(HealthMeasureLog log, String gender, Integer age) {
        BigDecimal bmiBd = log.getBmi();
        BigDecimal bfrBd = log.getBfr();
        BigDecimal bmrBd = log.getBmr();
        BigDecimal mrBd = log.getMr();
        BigDecimal wrBd = log.getWr();
        Integer uvi = log.getUvi();
        BigDecimal bqBd = log.getBq();
        BigDecimal prBd = log.getPr();
        double weight = log.getWeight() == null ? 0 : log.getWeight().doubleValue();

        String low = ConstEnum.SCALES_LOW.ename();
        String normal = ConstEnum.SCALES_NORMAL.ename();
        String high = ConstEnum.SCALES_HIGH.ename();
        String veryhigh = ConstEnum.SCALES_VERYHIGH.ename();

        // 判断身体质量指数标准
        if (bmiBd != null) {
            double bmi = bmiBd.doubleValue();
            if (bmi < 18.5) {
                this.bmi = low;
            } else if (18.5 <= bmi && bmi < 24.0) {
                this.bmi = normal;
            } else if (24.0 <= bmi && bmi < 28.0) {
                this.bmi = high;
            } else if (bmi >= 28.0) {
                this.bmi = veryhigh;
            }
        }

        // 判断内脏脂肪指数
        if (uvi != null) {
            if (uvi < 9) {
                this.uvi = normal;
            } else if (9 <= uvi && uvi < 14) {
                this.uvi = high;
            } else if (14 <= uvi && uvi <= 30) {
                this.uvi = veryhigh;
            }
        }

        // 区分性别
        switch (gender) {
            case ConstVal.USER_GENDER_MALE:
                // 判断体脂率
                if (bfrBd != null) {
                    double bfr = bfrBd.doubleValue();
                    if (age < 30) {
                        if (bfr < 0.1) {
                            this.bfr = low;
                        } else if (0.1 <= bfr && bfr < 0.21) {
                            this.bfr = normal;
                        } else if (0.21 <= bfr && bfr < 0.26) {
                            this.bfr = high;
                        } else if (bfr >= 0.26) {
                            this.bfr = veryhigh;
                        }
                    } else if (age >= 30) {
                        if (bfr < 0.11) {
                            this.bfr = low;
                        } else if (0.11 <= bfr && bfr < 0.22) {
                            this.bfr = normal;
                        } else if (0.22 <= bfr && bfr < 0.27) {
                            this.bfr = high;
                        } else if (bfr >= 0.27) {
                            this.bfr = veryhigh;
                        }
                    }
                }
                // 判断基础代谢率
                if (bmrBd != null) {
                    double bmr = bmrBd.doubleValue();
                    double standard = 0;
                    if (0 <= age && age < 3) {
                        standard = 60.9 * weight - 54.0;
                    } else if (3 <= age && age < 10) {
                        standard = 22.7 * weight + 495.0;
                    } else if (10 <= age && age < 18) {
                        standard = 17.5 * weight + 651.0;
                    } else if (18 <= age && age < 30) {
                        standard = 15.3 * weight + 679.0;
                    } else if (30 <= age) {
                        standard = 11.6 * weight + 876.0;
                    }
                    if (bmr < standard) {
                        this.bmr = low;
                    } else {
                        this.bmr = normal;
                    }
                }
                // 判断肌肉率
                if (mrBd != null) {
                    double mr = mrBd.doubleValue();
                    if (mr < 0.4) {
                        this.mr = low;
                    } else if (0.4 <= mr && mr < 0.6) {
                        this.mr = normal;
                    } else if (mr >= 0.6) {
                        this.mr = high;
                    }
                }
                // 判断水含量
                if (wrBd != null) {
                    double wr = wrBd.doubleValue();
                    if (wr < 0.55) {
                        this.wr = low;
                    } else if (0.55 <= wr && wr < 0.65) {
                        this.wr = normal;
                    } else if (wr >= 0.65) {
                        this.wr = high;
                    }
                }
                // 判断骨量
                if (bqBd != null) {
                    double bq = bqBd.doubleValue();
                    if (weight < 60.0) {
                        if (bq < 2.4) {
                            this.bq = low;
                        } else if (2.4 <= bq && bq <= 2.6) {
                            this.bq = normal;
                        } else if (bq > 2.6) {
                            this.bq = high;
                        }
                    } else if (60 <= weight && weight < 75) {
                        if (bq < 2.8) {
                            this.bq = low;
                        } else if (2.8 <= bq && bq <= 3.0) {
                            this.bq = normal;
                        } else if (bq > 3.0) {
                            this.bq = high;
                        }
                    } else if (weight >= 75) {
                        if (bq < 3.1) {
                            this.bq = low;
                        } else if (3.1 <= bq && bq <= 3.3) {
                            this.bq = normal;
                        } else if (bq > 3.3) {
                            this.bq = high;
                        }
                    }
                }
                // 判断蛋白质
                if (prBd != null) {
                    double pr = prBd.doubleValue();
                    if (pr < 0.16) {
                        this.pr = low;
                    } else if (0.16 <= pr && pr <= 0.18) {
                        this.pr = normal;
                    } else if (pr > 0.18) {
                        this.pr = high;
                    }
                }
                break;
            case ConstVal.USER_GENDER_FEMALE:
                // 判断体脂率
                if (bfrBd != null) {
                    double bfr = bfrBd.doubleValue();
                    if (age < 30) {
                        if (bfr < 0.16) {
                            this.bfr = low;
                        } else if (0.16 <= bfr && bfr < 0.24) {
                            this.bfr = normal;
                        } else if (0.24 <= bfr && bfr < 0.3) {
                            this.bfr = high;
                        } else if (bfr >= 0.3) {
                            this.bfr = veryhigh;
                        }
                    } else if (age >= 30) {
                        if (bfr < 0.19) {
                            this.bfr = low;
                        } else if (0.19 <= bfr && bfr < 0.27) {
                            this.bfr = normal;
                        } else if (0.27 <= bfr && bfr < 0.3) {
                            this.bfr = high;
                        } else if (bfr >= 0.3) {
                            this.bfr = veryhigh;
                        }
                    }
                }
                // 判断基础代谢率
                if (bmrBd != null) {
                    double bmr = bmrBd.doubleValue();
                    double standard = 0;
                    if (0 <= age && age < 3) {
                        standard = 61.0 * weight - 51.0;
                    } else if (3 <= age && age < 10) {
                        standard = 22.5 * weight + 499.0;
                    } else if (10 <= age && age < 18) {
                        standard = 12.2 * weight + 746.0;
                    } else if (18 <= age && age < 30) {
                        standard = 14.7 * weight + 496.0;
                    } else if (30 <= age) {
                        standard = 8.7 * weight + 820.0;
                    }
                    if (bmr < standard) {
                        this.bmr = low;
                    } else {
                        this.bmr = normal;
                    }
                }
                // 判断肌肉率
                if (mrBd != null) {
                    double mr = mrBd.doubleValue();
                    if (mr < 0.3) {
                        this.mr = low;
                    } else if (0.3 <= mr && mr < 0.5) {
                        this.mr = normal;
                    } else if (mr >= 0.5) {
                        this.mr = high;
                    }
                }
                // 判断水含量
                if (wrBd != null) {
                    double wr = wrBd.doubleValue();
                    if (wr < 0.45) {
                        this.wr = low;
                    } else if (0.45 <= wr && wr < 0.6) {
                        this.wr = normal;
                    } else if (wr >= 0.6) {
                        this.wr = high;
                    }
                }
                // 判断骨量
                if (bqBd != null) {
                    double bq = bqBd.doubleValue();
                    if (weight < 45.0) {
                        if (bq < 1.7) {
                            this.bq = low;
                        } else if (1.7 <= bq && bq <= 1.9) {
                            this.bq = normal;
                        } else if (bq > 1.9) {
                            this.bq = high;
                        }
                    } else if (45 <= weight && weight < 60) {
                        if (bq < 2.1) {
                            this.bq = low;
                        } else if (2.1 <= bq && bq <= 2.3) {
                            this.bq = normal;
                        } else if (bq > 2.3) {
                            this.bq = high;
                        }
                    } else if (weight >= 60) {
                        if (bq < 2.4) {
                            this.bq = low;
                        } else if (2.4 <= bq && bq <= 2.6) {
                            this.bq = normal;
                        } else if (bq > 2.6) {
                            this.bq = high;
                        }
                    }
                }
                // 判断蛋白质
                if (prBd != null) {
                    double pr = prBd.doubleValue();
                    if (pr < 0.16) {
                        this.pr = low;
                    } else if (0.16 <= pr && pr <= 0.18) {
                        this.pr = normal;
                    } else if (pr > 0.18) {
                        this.pr = high;
                    }
                }
                break;
            default:
        }
    }
}
