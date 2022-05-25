import {
  sidoList,
  gugunList,
  dongList,
  dealList,
  houseList,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sidoName) => {
        state.sidos.push({ value: sidoName, text: sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugunName) => {
        state.guguns.push({ value: gugunName, text: gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dongName) => {
        state.dongs.push({ value: dongName, text: dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ text: "시/군/구" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ text: "읍/면/동" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
      console.log("house", houses);
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
      console.log("house: ", house);
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoName) => {
      const params = {
        siguName: sidoName,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, params) => {
      // console.log(params);
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDealList: ({ commit }, params) => {
      dealList(
        params,
        (response) => {
          // console.log(response.data);
          commit("SET_HOUSE_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHouseList: ({ commit }, gugunCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      //   const SERVICE_KEY =
      //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
