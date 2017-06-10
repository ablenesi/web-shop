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
    public class CategoryController : ApiController
    {
        private IUnitOfWork _uow;

        public CategoryController(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public IHttpActionResult Post(CategoryDTO category)
        {
            try
            {
                _uow.CategoryOperations.Create(category);
                _uow.SaveChanges();
            }
            catch (Exception ex)
            {
                return InternalServerError(ex);
            }

            return Ok();
        }

        public IEnumerable<CategoryDTO> Get()
        {
            return _uow.CategoryOperations.Get();
        }
    }
}