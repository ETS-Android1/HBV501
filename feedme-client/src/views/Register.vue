<template>
    <v-card
        class="mx-auto"
        width="400"
        style="margin-top: 1rem;"
    >
    <v-card-title>FeedMe Register</v-card-title>
    <v-card-text>
        <v-form>
            <v-text-field
            v-model="username"
            :error-messages="nameErrors"
            label="Username"
            required
            @input="$v.username.$touch()"
            @blur="$v.username.$touch()"
            prepend-inner-icon="mdi-account"
            ></v-text-field>
            <v-text-field
            v-model="email"
            :error-messages="emailErrors"
            label="Email"
            required
            @input="$v.email.$touch()"
            @blur="$v.email.$touch()"
            prepend-inner-icon="mdi-email"
            ></v-text-field>
            <v-text-field
            v-model="password"
            :error-messages="passwordErrors"
            label="Password"
            required
            @input="$v.password.$touch()"
            @blur="$v.password.$touch()"
            prepend-inner-icon="mdi-lock"
            type="password"
            ></v-text-field>
             <v-text-field
            v-model="confirmPassword"
            :error-messages="confirmErrors"
            label="Confirm password"
            required
            @input="$v.confirmPassword.$touch()"
            @blur="$v.confirmPassword.$touch()"
            prepend-inner-icon="mdi-lock-question"
            type="password"
            ></v-text-field>
            <v-btn
            color="orange darken-1"
            class="mr-4"
            width="100%"
            @click="submit"
            >
            Register
            </v-btn>          
        </v-form>
    </v-card-text>
    </v-card>
</template>

<script>
import { validationMixin } from 'vuelidate'
import { required, maxLength, email, minLength, sameAs } from 'vuelidate/lib/validators'
export default ({
    name: 'Register',
    mixins: [validationMixin],
    validations: {
      username: { required, maxLength: maxLength(12)},
      email: { required, email },
      password: { required, minLength: minLength(6) },
      confirmPassword: { required, sameAsPassword: sameAs('password') }
    },
    data() {
        return {
            username: "",
            password: "",
            email: "",
            confirmPassword: ""
        }
    },
    computed: {
        nameErrors () {
        const errors = []
        if (!this.$v.username.$dirty) return errors
        !this.$v.username.maxLength && errors.push('Username must be at most 12 characters long')
        !this.$v.username.required && errors.push('Username is required.')
        return errors
      },
      emailErrors () {
        const errors = []
        if (!this.$v.email.$dirty) return errors
        !this.$v.email.email && errors.push('Must be valid e-mail')
        !this.$v.email.required && errors.push('E-mail is required')
        return errors
      },
      passwordErrors() {
        const errors = []
        if (!this.$v.password.$dirty) return errors
        !this.$v.password.minLength && errors.push('Password must be at least 6 characters long')
        !this.$v.password.required && errors.push('Password is required')
        return errors
      },
      confirmErrors() {
        const errors = []
        if (!this.$v.confirmPassword.$dirty) return errors
        !this.$v.confirmPassword.sameAsPassword && errors.push('The password does not match')
        !this.$v.confirmPassword.required && errors.push('Password confirmation is required')
        return errors
      }
    },
    methods: {
        submit () {
            this.$v.$touch()    
            if(!this.$v.$anyError) {
                //can submit
                console.log('Lets pretend Im submitting')
            }
        },
        clear () {
            this.$v.$reset()
            this.name = ''
            this.email = ''
            this.password = ''
            this.confirmPassword = ''
        }
    },
    async mounted() {
        this.clear();
    }
})
</script>
