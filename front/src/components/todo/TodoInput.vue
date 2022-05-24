<template>
  <b-container class="bv-example-row">
    <b-row class="text-center">
      <b-col>
        <b-input-group prepend="할일" class="mt-3">
          <b-form-input
            v-model.trim="todoTitle"
            @keypress.enter="registerTodo"
          ></b-form-input>
          <b-input-group-append>
            <b-button variant="outline-success" @click="registerTodo"
              >등록</b-button
            >
          </b-input-group-append>
        </b-input-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const todoStore = "todoStore";

export default {
  name: "TodoInput",
  data() {
    return {
      todoTitle: "",
    };
  },
  methods: {
    ...mapActions(todoStore, ["createTodo"]),
    registerTodo() {
      //   console.log('Create Todo!!');
      //   console.log(this.$store);
      const todoItem = {
        title: this.todoTitle,
        completed: false,
      };
      //   this.$store.commit('CREATE_TODO', todoItem);
      if (todoItem.title) this.createTodo(todoItem);
      // this.$store.dispatch("createTodo", todoItem);

      this.todoTitle = "";
    },
  },
};
</script>

<style></style>
