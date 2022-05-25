<template>
  <tbody>
    <tr>
      <td>{{ cwriter }}</td>
      <td>{{ ccontent }}</td>
      <td>{{ cregtime }}</td>
      <td>
        <button @click="commentForm">답글작성</button>
      </td>
      <td>
        <button v-if="cwriter == userInfo.userid">수정</button>
      </td>
      <td>
        <button
          @click="deleteComment"
          v-if="cwriter == userInfo.userid || userInfo.role == 'ROLE_ADMIN'"
        >
          삭제
        </button>
      </td>
    </tr>
    <tr v-if="reply">
      <td>
        <form @submit="onSubmit">
          <input type="text" v-model="comment" />
          <button type="submit">등록</button>
        </form>
      </td>
    </tr>
  </tbody>
</template>

<script>
import { deleteComment, writeComment } from "@/api/comment";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentListItem",
  props: {
    cno: Number,
    bno: Number,
    cwriter: String,
    ccontent: String,
    cregtime: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      reply: false,
      comment: "",
    };
  },
  methods: {
    commentForm() {
      this.reply = !this.reply;
      console.log(this.reply);
    },
    deleteComment() {
      deleteComment(
        this.cno,
        () => {
          this.$emit("refresh");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.comment && ((msg = "댓글을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.registComment();
    },
    registComment() {
      console.log(this.userInfo.userid);
      writeComment(
        this.cno,
        {
          bno: this.bno,
          ccontent: this.comment,
          cwriter: this.userInfo.userid,
        },
        () => {
          this.comment = "";
          this.commentForm();
          this.$emit("refresh");
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style></style>
