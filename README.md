# Read Me First

The original idea is from  
https://blog.devgenius.io/enhancing-your-spring-boot-rest-api-with-filters-e9e650fca35a

See  
https://medium.com/@sehgal.mohit06/custom-filters-and-onceperrequestfilter-in-spring-boot-48d861bafdba

# Two ways to create filters

**1 Annotation method**

- Implement the Filter interface and add the @WebFilter annotation on top of the class.
- (2) Add the @Order() annotation on the filter class to specify the order of the filters.
- (3) Add the @ServletComponentScan annotation on the Spring Boot startup class.

**2 Registering the filter as a Bean**

- Create a filter class that implements the Filter interface.
- Create a filter configuration class. Inside it, create a FilterRegistrationBean and register the previously created
  filter class into it.
-

# Filter Class

**public void doFilter(ServletRequest, ServletResponse, FilterChain)**
This method completes the actual filtering operation. When the client request method matches the URL set by the filter,
the Servlet container will first call the doFilter method of the filter. FilterChain is used to access subsequent
filters.

**public void init(FilterConfig filterConfig)**
When the web application starts, the web server will create an instance object of the Filter and call its init method to
complete the initialization
function of the object (the filter object will only be created once, and the init method will only be executed once).
Developers can perform some initialization operations through the parameters of the init method, such as reading
configuration files.

**public void destroy()**
The Servlet container calls this method before destroying the filter instance.
In this method, the resources occupied by the Servlet filter are released.
