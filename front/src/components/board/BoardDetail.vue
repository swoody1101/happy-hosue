<template>
  <div>
    <h3 class="title">게시글 작성</h3>
    <table class="article-table">
      <tr>
        <th class="td-title">글번호</th>
        <td>{{ article.bno }}</td>
        <th class="td-title">작성자</th>
        <td>{{ article.bwriter }}</td>
        <th class="td-title">작성일</th>
        <td>{{ article.bregtime }}</td>
        <th class="td-title">조회</th>
        <td>{{ article.breadCount + 1 }}</td>
      </tr>
      <tr class="article-title-container">
        <th class="td-title">제목</th>
        <td colspan="7">{{ article.btitle }}</td>
      </tr>
      <tr>
        <td colspan="8" class="article-content">
          {{ article.bcontent }}
        </td>
      </tr>
    </table>

    <div class="btn-container">
      <button
        @click="moveModifyArticle"
        v-if="article.bwriter == userInfo.userid"
        class="write-btn"
      >
        수정
      </button>
      <button
        @click="deleteArticle"
        v-if="
          article.bwriter == userInfo.userid || userInfo.role == 'ROLE_ADMIN'
        "
        class="clear-btn"
      >
        삭제
      </button>
    </div>

    <comment-list :bno="bno"></comment-list>
  </div>
</template>

<script>
import CommentList from "@/components/board/comment/CommentList.vue";
import { getArticle, deleteArticle, readCountUpArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  components: { CommentList },
  data() {
    return {
      bno: 0,
      article: {},
    };
  },
  created() {
    this.bno = this.$route.params.articleno;
    this.breadCountUp();
    getArticle(
      this.bno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.bno },
      });
      console.log("bno", this.article.bno);
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.bno, () => {
          this.$router.push({ name: "boardList" }),
            (error) => {
              console.log(error);
            };
        });
      }
    },
    breadCountUp() {
      readCountUpArticle(
        this.bno,
        () => {},
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.td-title {
  background-color: rgb(244, 244, 244);
  font-weight: bold;
  color: rgb(84, 84, 84);
}

.title {
  margin-left: 50px;
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
}

.article-table td {
  padding: 5px;
}

.article-table {
  width: 100%;
  border: 2px solid rgb(182, 182, 182);
  border-radius: 5px;
  /* border-bottom: 3px solid rgb(149, 149, 149); */
}

.article-table tr {
  border-top: 1px solid rgba(214, 214, 214, 0.498);
}

.article-content {
  height: 300px;
  border-top: 1px solid rgba(64, 64, 64, 0.498);
  text-align: left;
}

.article-title-container td {
  text-align: left;
}

.write-btn {
  padding: 15px 25px;
  border: none;
  color: whitesmoke;
  background-color: rgb(165, 165, 165);
  border-radius: 5px;
  font-weight: bolder;
  margin-right: 10px;
}

.clear-btn {
  padding: 13px 23px;
  border: 1px solid rgb(138, 138, 138);
  color: rgb(138, 138, 138);
  background-color: whitesmoke;
  border-radius: 5px;
  font-weight: bolder;
}

.btn-container {
  margin: 10px 0;
}
</style>
