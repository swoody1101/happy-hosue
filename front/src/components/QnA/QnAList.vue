<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h5 class="title">QnA 목록(전체 {{ totalCnt }}개)</h5>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="qnas.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
              <b-th>작성자</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <qna-list-item v-for="qna in qnas" :key="qna.qna_no" v-bind="qna" />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <div class="page-container">
      <b-button @click="prePage()">[이전]</b-button>
      <b-button
        v-for="index in pagesize"
        :key="index"
        @click="movePage(index + startPage - 1)"
        >{{ index + startPage - 1 }}
      </b-button>
      <b-button @click="nextPage()">[다음]</b-button>
    </div>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const api = apiInstance();

import QnaListItem from "@/components/QnA/item/QnaListItem";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

export default {
  name: "QnAList",
  components: {
    QnaListItem,
  },
  data() {
    return {
      qnas: [],
      page: 1,
      startPage: 1,
      endPage: 1,
      totalPage: 1,
      totalCnt: 0,
    };
  },
  created() {
    this.movePage(1);
  },
  computed: {
    pagesize() {
      return this.endPage - this.startPage + 1;
    },
  },
  methods: {
    movePage(p) {
      api.get(`/qna/list?p=${p}`).then(({ data }) => {
        console.log(data);
        this.qnas = data.qnaList;
        this.page = data.page;
        this.startPage = data.startPage;
        this.endPage = data.endPage;
        this.totalPage = data.totalPage;
        this.totalCnt = data.qnaList.length;
      });
    },
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
    prePage() {
      if (this.page > 1) {
        this.movePage(this.page - 1);
      }
    },
    nextPage() {
      if (this.page < this.endPage) {
        this.movePage(this.page + 1);
      }
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.title {
  margin-left: 20px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}
.tdClass {
  width: 50px;
  text-align: center;
}

.tdSubject {
  width: 300px;
  text-align: left;
}

.page-container {
  text-align: center;
}
</style>
