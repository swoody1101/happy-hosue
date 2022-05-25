<template>
  <div class="tableContainer">
    <h5 class="title">글 목록(전체 {{ totalCnt }}개)</h5>
    <div class="search-container">
      <form @submit="searchArticle">
        검색:
        <input
          type="text"
          v-model="keyword"
          placeholder="제목 / 내용 / 작성자명으로 검색하세요."
          class="search-keyword-box"
        />
        <button type="submit" class="search-btn">검색</button>
      </form>
    </div>
    <div>
      <table class="articleTb">
        <thead>
          <tr>
            <th>글번호</th>
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
            class="notice article-item"
          />
          <board-list-item
            v-for="article in articles"
            :key="'article' + article.bno"
            v-bind="article"
            class="article-item"
          />
        </tbody>
      </table>
      <div class="write-btn-container">
        <button class="write-btn" @click="moveWrite()">글 쓰기</button>
      </div>
      <button @click="prePage()" class="page-change page-btn">
        이전 페이지
      </button>
      <button
        class="page-select page-btn"
        v-for="index in totalPage"
        :key="index"
        @click="movePage(index + startPage - 1)"
      >
        {{ index + startPage - 1 }}
      </button>
      <button @click="nextPage()" class="page-change page-btn">
        다음 페이지
      </button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { listArticle, listNotice } from "@/api/board";

import BoardListItem from "@/components/board/item/BoardListItem.vue";
import BoardNoticeListItem from "@/components/board/item/BoardNoticeListItem.vue";

const memberStore = "memberStore";

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
    this.movePage(1);
  },
  computed: {
    ...mapState(memberStore, ["loginInfo"]),

    pagesize() {
      return this.endPage - this.startPage + 1;
    },
    totalCnt() {
      return this.articles.length;
    },
  },
  methods: {
    movePage(p) {
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
    searchArticle(event) {
      event.preventDefault();
      this.movePage(this.page);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.articleTb {
  width: 100%;
  border-top: 4px solid rgb(228, 228, 228);
  border-bottom: 4px solid rgb(228, 228, 228);
}

.articleTb thead tr th {
  padding: 15px 20px;
}

.articleTb tbody tr {
  border-top: 1px solid rgba(214, 214, 214, 0.498);
}

.notice {
  background-color: rgba(255, 213, 213, 0.239);
}

.title {
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}

.article-title {
  color: black;
  text-align: left;
}

.write-btn-container {
  margin-top: 20px;
  margin-right: 30px;
  text-align: right;
}

.write-btn {
  padding: 15px 25px;
  border: none;
  color: whitesmoke;
  background-color: rgb(138, 138, 138);
  border-radius: 5px;
  font-weight: bolder;
}

.search-container {
  text-align: right;
  margin-bottom: 20px;
  margin-right: 20px;
}
.search-keyword-box {
  border-radius: 3px;
  border: 0.5px gray solid;
  padding: 5px 10px;
  margin-right: 5px;
  width: 300px;
}
.search-btn {
  padding: 5px 10px;
  border-radius: 3px;
  border: none;
  color: whitesmoke;
  background-color: rgb(138, 138, 138);
}

.page-btn {
  border: 1px rgba(148, 148, 148, 0.545) solid;
  padding: 5px 10px;
  background-color: white;
  margin-bottom: 30px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: bold;
}
</style>
