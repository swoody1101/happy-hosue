<template>
  <div>
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoName"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunName"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="dongName" :options="dongs"></b-form-select>
      </b-col>
    </b-row>
    <form @submit="searchApt">
      <input
        type="text"
        v-model="keyword"
        placeholder="아파트 이름을 입력하세요."
      />
      <button type="submit">검색</button>
    </form>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      keyword: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getDealList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      // console.log("sido", this.sidoName);
      this.CLEAR_GUGUN_LIST();
      this.gugunName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      // console.log("gu", this.gugunName);
      this.CLEAR_DONG_LIST();
      this.dongName = null;
      let params = {
        siguName: this.sidoName,
        gugunName: this.gugunName,
      };
      if (this.gugunName) this.getDong(params);
    },
    searchApt(event) {
      event.preventDefault();
      let params = {
        siguName: this.sidoName,
        gugunName: this.gugunName,
        dongName: this.dongName,
        keyword: this.keyword,
      };
      if (this.dongName) this.getDealList(params);
    },
  },
};
</script>

<style></style>
