/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.lht.lhttalk.module.friend.pojo;

import com.lht.lhttalk.module.friend.HanziToLetterUtil;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/8/21.
 */

public class FriendInfoResBean {
    private String fristLetter;
    private String username;
    private String nickname;
    private String avatar;
    private String pinyin;

    public String getFristLetter() {
        return fristLetter;
    }

    public void setFristLetter(String fristLetter) {
        this.fristLetter = fristLetter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    private static ArrayList<FriendInfoResBean> arrayList = new ArrayList<>();

    public static ArrayList<FriendInfoResBean> getAllFriendInfo(FriendList friendList) {
        arrayList.clear();
        getAA(friendList);
        getBB(friendList);
        getCC(friendList);
        getDD(friendList);
        getEE(friendList);
        getFF(friendList);
        getGG(friendList);
        getHH(friendList);
        getII(friendList);
        getJJ(friendList);
        getKK(friendList);
        getLL(friendList);
        getMM(friendList);
        getNN(friendList);
        getOO(friendList);
        getPP(friendList);
        getQQ(friendList);
        getRR(friendList);
        getSS(friendList);
        getTT(friendList);
        getUU(friendList);
        getVV(friendList);
        getWW(friendList);
        getXX(friendList);
        getYY(friendList);
        getZZ(friendList);
        return arrayList;
    }


    /**
     * A
     *
     * @param friendList
     */
    private static void getAA(FriendList friendList) {
        ArrayList<FriendBasicPojo> aa = friendList.getA();
        if (aa != null && aa.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : aa) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * B
     *
     * @param friendList
     */
    private static void getBB(FriendList friendList) {
        ArrayList<FriendBasicPojo> bb = friendList.getB();
        if (bb != null && bb.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : bb) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * C
     *
     * @param friendList
     */
    private static void getCC(FriendList friendList) {
        ArrayList<FriendBasicPojo> cc = friendList.getA();
        if (cc != null && cc.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : cc) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * D
     *
     * @param friendList
     */
    private static void getDD(FriendList friendList) {
        ArrayList<FriendBasicPojo> dd = friendList.getA();
        if (dd != null && dd.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : dd) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * E
     *
     * @param friendList
     */
    private static void getEE(FriendList friendList) {
        ArrayList<FriendBasicPojo> ee = friendList.getA();
        if (ee != null && ee.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ee) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * F
     *
     * @param friendList
     */
    private static void getFF(FriendList friendList) {
        ArrayList<FriendBasicPojo> ff = friendList.getF();
        if (ff != null && ff.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ff) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * G
     *
     * @param friendList
     */
    private static void getGG(FriendList friendList) {
        ArrayList<FriendBasicPojo> gg = friendList.getG();
        if (gg != null && gg.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : gg) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * H
     *
     * @param friendList
     */
    private static void getHH(FriendList friendList) {
        ArrayList<FriendBasicPojo> hh = friendList.getH();
        if (hh != null && hh.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : hh) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * I
     *
     * @param friendList
     */
    private static void getII(FriendList friendList) {
        ArrayList<FriendBasicPojo> ii = friendList.getI();
        if (ii != null && ii.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ii) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * J
     *
     * @param friendList
     */
    private static void getJJ(FriendList friendList) {
        ArrayList<FriendBasicPojo> jj = friendList.getJ();
        if (jj != null && jj.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : jj) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * K
     *
     * @param friendList
     */
    private static void getKK(FriendList friendList) {
        ArrayList<FriendBasicPojo> kk = friendList.getK();
        if (kk != null && kk.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : kk) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * L
     *
     * @param friendList
     */
    private static void getLL(FriendList friendList) {
        ArrayList<FriendBasicPojo> ll = friendList.getL();
        if (ll != null && ll.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ll) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * M
     *
     * @param friendList
     */
    private static void getMM(FriendList friendList) {
        ArrayList<FriendBasicPojo> mm = friendList.getM();
        if (mm != null && mm.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : mm) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * N
     *
     * @param friendList
     */
    private static void getNN(FriendList friendList) {
        ArrayList<FriendBasicPojo> nn = friendList.getN();
        if (nn != null && nn.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : nn) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * O
     *
     * @param friendList
     */
    private static void getOO(FriendList friendList) {
        ArrayList<FriendBasicPojo> oo = friendList.getO();
        if (oo != null && oo.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : oo) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * P
     *
     * @param friendList
     */
    private static void getPP(FriendList friendList) {
        ArrayList<FriendBasicPojo> pp = friendList.getP();
        if (pp != null && pp.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : pp) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * Q
     *
     * @param friendList
     */
    private static void getQQ(FriendList friendList) {
        ArrayList<FriendBasicPojo> qq = friendList.getQ();
        if (qq != null && qq.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : qq) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * R
     *
     * @param friendList
     */
    private static void getRR(FriendList friendList) {
        ArrayList<FriendBasicPojo> rr = friendList.getR();
        if (rr != null && rr.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : rr) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * S
     *
     * @param friendList
     */
    private static void getSS(FriendList friendList) {
        ArrayList<FriendBasicPojo> ss = friendList.getS();
        if (ss != null && ss.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ss) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * T
     *
     * @param friendList
     */
    private static void getTT(FriendList friendList) {
        ArrayList<FriendBasicPojo> tt = friendList.getT();
        if (tt != null && tt.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : tt) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * U
     *
     * @param friendList
     */
    private static void getUU(FriendList friendList) {
        ArrayList<FriendBasicPojo> uu = friendList.getU();
        if (uu != null && uu.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : uu) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * V
     *
     * @param friendList
     */
    private static void getVV(FriendList friendList) {
        ArrayList<FriendBasicPojo> vv = friendList.getV();
        if (vv != null && vv.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : vv) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * W
     *
     * @param friendList
     */
    private static void getWW(FriendList friendList) {
        ArrayList<FriendBasicPojo> ww = friendList.getW();
        if (ww != null && ww.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : ww) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * X
     *
     * @param friendList
     */
    private static void getXX(FriendList friendList) {
        ArrayList<FriendBasicPojo> xx = friendList.getX();
        if (xx != null && xx.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : xx) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * Y
     *
     * @param friendList
     */
    private static void getYY(FriendList friendList) {
        ArrayList<FriendBasicPojo> yy = friendList.getY();
        if (yy != null && yy.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : yy) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * Z
     *
     * @param friendList
     */
    private static void getZZ(FriendList friendList) {
        ArrayList<FriendBasicPojo> zz = friendList.getZ();
        if (zz != null && zz.size() != 0) {
            for (FriendBasicPojo friendBasicPojo : zz) {
                copyToBean(friendBasicPojo);
            }
        }
    }

    /**
     * 将FriendBasicPojo copy 到FriendInfoResBean
     *
     * @param friendBasicPojo
     */
    private static void copyToBean(FriendBasicPojo friendBasicPojo) {
        FriendInfoResBean bean = new FriendInfoResBean();
        //取出username 首字母
        String pinYinFirstLetter = HanziToLetterUtil.getPinYinFirstLetter(friendBasicPojo.getUsername());
        bean.setFristLetter(pinYinFirstLetter);
        bean.setUsername(friendBasicPojo.getUsername());
        bean.setAvatar(friendBasicPojo.getAvatar());
        bean.setNickname(friendBasicPojo.getNickname());
        bean.setPinyin(friendBasicPojo.getPinyin());
        arrayList.add(bean);
    }
}
