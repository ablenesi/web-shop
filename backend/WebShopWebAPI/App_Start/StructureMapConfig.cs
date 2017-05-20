using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web.Http;
using Microsoft.Owin.Security.OAuth;
using Newtonsoft.Json.Serialization;
using WebShop.IoC.DI;

namespace WebShopWebAPI
{
    public static class StructureMapConfig
    {
        public static void Register(HttpConfiguration config)
        {
            config.DependencyResolver = new DependencyResolver(Resolver.Container);
        }
    }
}
