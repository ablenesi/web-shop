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
    public class OrderController : ApiController
    {
        private IUnitOfWork _uow;

        public OrderController(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public IHttpActionResult Post(OrderDTO order)
        {
            try
            {
                _uow.OrderOperations.Create(order);
                _uow.SaveChanges();
            }
            catch (Exception ex)
            {
                return InternalServerError(ex);
            }

            return Ok();
        }

        public IEnumerable<OrderDTO> Get()
        {
            return _uow.OrderOperations.Get();
        }
    }
}