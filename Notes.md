0.3.0
There is one thing I am confused about order of configuration in WebSecurityConfigurerAdapter.
Currently this:

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sing-up", "/ping", "/sing-in")
                .permitAll()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
                
Works fine. But I tried to do: 

        http.authorizeRequests()
                .antMatchers("/sing-up", "/ping", "/sing-in")
                .permitAll()
                .antMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
                
and it doesn't work, I don't know why, /ping works fine and /sing-up is not. I don't know. For know I will use first version.