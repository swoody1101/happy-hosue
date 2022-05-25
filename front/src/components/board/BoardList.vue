<template>
  <div>
    <h2>BoardList</h2>
    <div><h2>글 목록</h2></div>
    <button @click="moveWrite()">글 쓰기</button>
    <form @submit="searchArticle">
      <input type="text" v-model="keyword" placeholder="검색어를 입력하세요." />
      <button type="submit">검색</button>
    </form>
    <div>
      <table border="1" width="500px">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <board-notice-list-item
            v-for="notice in notices"
            :key="'notice' + notice.bno"
            v-bind="notice"
          />
          <board-list-item
            v-for="article in articles"
            :key="'article' + article.bno"
            v-bind="article"
          />
        </tbody>
      </table>
      <button @click="prePage()">[이전]</button>
      <button
        v-for="index in pagesize"
        :key="index"
        @click="makePage(index + startPage - 1)"
      >
        {{ index + startPage - 1 }}
      </button>
      <button @click="nextPage()">[다음]</button>
    </div>
  </div>
</template>

<script>
import { listArticle, listNotice } from "@/api/board";

import BoardListItem from "@/components/board/item/BoardListItem.vue";
import BoardNoticeListItem from "@/components/board/item/BoardNoticeListItem.vue";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
    BoardNoticeListItem,
  },
  data() {
    return {
      keyword: "",
      page: 1,
      startPage: 1,
      totalPage: 1,
      endPage: 1,
      notices: [],
      articles: [],
    };
  },
  created() {
    listNotice(
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
    this.makePage(1);
  },
  computed: {
    pagesize() {
      return this.endPage - this.startPage + 1;
    },
  },
  methods: {
    makePage(p) {
      this.page = p;
      listArticle(
        this.page,
        this.keyword,
        (response) => {
          // console.log(response.data);
          this.page = response.data.page;
          this.articles = response.data.boardList;
          this.startPage = response.data.startPage;
          this.totalPage = response.data.totalPage;
          this.endPage = response.data.endPage;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    movePage(p) {
      this.makePage(p);
    },
    prePage() {
      if (this.page > 10) {
        this.makePage(Math.floor((this.page - 10) / 10) * 10 + 1);
      }
    },
    nextPage() {
      if (this.page < Math.floor((this.endPage * 10) / 10)) {
        this.makePage(Math.floor((this.page + 10) / 10) * 10 + 1);
      }
    },
    searchArticle(event) {
      event.preventDefault();
      this.makePage(this.page);
    },
  },
};
</script>

<style></style>
