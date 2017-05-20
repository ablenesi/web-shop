using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.IoC.DI;

namespace WebShopWebAPI.Controllers
{
    public class ProductController : ApiController
    {
        private IUnitOfWork _uow;

        public ProductController(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public IHttpActionResult Post(ProductDTO product)
        {
            try
            {
                _uow.ProductOperations.Create(product);
                _uow.SaveChanges();
            }
            catch (Exception ex)
            {
                return InternalServerError(ex);
            }

            return Ok();
        }

        public IEnumerable<ProductDTO> Get()
        {
            return _uow.ProductOperations.Get();
        }
    }
}
