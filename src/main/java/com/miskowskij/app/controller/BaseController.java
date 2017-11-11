package com.miskowskij.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = { "/" }, produces="application/api-v1+json")
abstract public class BaseController { }
